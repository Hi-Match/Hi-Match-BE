package kr.co.himatch.thanksyouplz.code.controller;

import kr.co.himatch.thanksyouplz.code.dto.*;
import kr.co.himatch.thanksyouplz.code.entity.QuestionType;
import kr.co.himatch.thanksyouplz.code.service.CodeService;
import kr.co.himatch.thanksyouplz.code.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    // 인성검사 후 인재상 코드를 AI로 불러오는 함수
    private Mono<String> callGeminiForCode(List<CodeMemberResultRequestDTO> requestDTOList, String codeFileContent) {
        StringBuilder sb = new StringBuilder();
        for (CodeMemberResultRequestDTO requestDTO : requestDTOList) {
            sb.append(requestDTO.getQuestion());
            sb.append(" -> ");
            sb.append(requestDTO.getResponse());
            sb.append("  ");
        }
        String codePrompt = sb.toString() + "당신은 인재상 분석 전문가입니다. 제공된 질문과 답변을 바탕으로, 이 사람의 핵심 인재상 코드 **알파벳 네 자리**만 정확하게 알려주세요. **결과만** 응답해야 하며, 다른 설명이나 괄호, 따옴표 등의 불필요한 문자는 포함하지 마세요. \n\n" + codeFileContent;
        return codeService.callGemini(codePrompt);
    }

    // 인성검사 후 인재상 상세 설명을 AI로 불러오는 함수
    private Mono<String> callGeminiForDescription(List<CodeMemberResultRequestDTO> requestDTOList, String codeFileContent) {
        StringBuilder sb = new StringBuilder();
        for (CodeMemberResultRequestDTO requestDTO : requestDTOList) {
            sb.append(requestDTO.getQuestion());
            sb.append(" -> ");
            sb.append(requestDTO.getResponse());
            sb.append("  ");
        }
        String descriptionPrompt = sb.toString() + "당신은 인재상 분석 전문가입니다. 제공된 질문과 답변, 그리고 인재상 코드 정보를 바탕으로 다음을 분석하고 정리해주세요.\n\n 1. 각 인재상 코드별 비율을 명확하게 분석하여 제시해주세요.\n2. 이 사람의 업무와 관련된 강점과 약점을 구체적인 근거를 바탕으로 정리해주세요. \n\n" + codeFileContent;
        return codeService.callGemini(descriptionPrompt);
    }

    // 인성검사 후 인재상 적합 여부를 AI로 불러오는 함수
    private Mono<String> callGeminiForSuitability(List<CodeMemberResultRequestDTO> requestDTOList, String suitabilityFileContent) {
        StringBuilder sb = new StringBuilder();
        for (CodeMemberResultRequestDTO requestDTO : requestDTOList) {
            sb.append(requestDTO.getQuestion());
            sb.append(" -> ");
            sb.append(requestDTO.getResponse());
            sb.append("  ");
        }
        String suitabilityPrompt = sb.toString() + "당신은 채용 적합성 평가 전문가입니다. 제공된 질문과 답변, 그리고 관련 정보를 바탕으로 이 지원자가 해당 직무에 '적합'한지 혹은 '부적합'한지 명확하게 판단하여 단어로만 알려주세요. 판단 근거는 내부적으로 활용하며, 외부로 설명할 필요는 없습니다. \n\n" + suitabilityFileContent;
        return codeService.callGemini(suitabilityPrompt);
    }

    // 개인 인성검사 - 검사 결과 API
    @PostMapping("/member/result")
    public Mono<ResponseEntity<CodeMemberResultResponseDTO>> memberResult(@RequestBody List<CodeMemberResultRequestDTO> requestDTOList) throws Exception {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        String codeFileContent = FileUtil.readResourceFile("code.txt");
        String suitabilityFileContent = FileUtil.readResourceFile("suitability.txt");

        Mono<String> codeMono = callGeminiForCode(requestDTOList, codeFileContent);
        Mono<String> descriptionMono = callGeminiForDescription(requestDTOList, codeFileContent);
        Mono<String> suitabilityMono = callGeminiForSuitability(requestDTOList, suitabilityFileContent);

        List<Mono<String>> monosToZip = Arrays.asList(codeMono, descriptionMono, suitabilityMono);

        return Mono.zip(monosToZip, results -> {
                    String code = (String) results[0];
                    String description = (String) results[1];
                    String suitability = (String) results[2];
                    codeService.changeMemberCode(memberNo, suitability, description, code);
                    return new CodeMemberResultResponseDTO(code, description);
                })
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    //개인 인성검사 - 문제 조회 API - A유형(예/아니오) 120문제
    @GetMapping("/member/question-list-a")
    public ResponseEntity<?> memberQuestionListA() {
        List<CodeMemberQuestionListResponseDTO> list = codeService.selectQuestionList(QuestionType.A);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //개인 인성검사 - 문제 조회 API - B유형(6단계) 100문제
    @GetMapping("/member/question-list-b")
    public ResponseEntity<?> memberQuestionListB() {
        List<CodeMemberQuestionListResponseDTO> list = codeService.selectQuestionList(QuestionType.B);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //최근 응시 내역 시간 API
    @GetMapping("/member/time")
    public ResponseEntity<?> memberTime() {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        CodeMemberTimeResponseDTO responseDTO = codeService.selectCodeTime(memberNo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //기업 인재상 등록 API
    @PostMapping("/company/register")
    public ResponseEntity<?> companyRegister(@RequestBody CodeCompanyRegisterRequestDTO requestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        CodeCompanyRegisterResponseDTO responseDTO = codeService.companyCodeRegister(memberNo, requestDTO.getCode().getColumData());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}