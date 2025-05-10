package kr.co.himatch.thanksyouplz.code.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private String fromDtoToString(List<CodeMemberResultRequestDTO> requestDTOList) {
        StringBuilder sb = new StringBuilder();
        for (CodeMemberResultRequestDTO requestDTO : requestDTOList) {
            sb.append(requestDTO.getQuestion());
            sb.append(" -> ");
            sb.append(requestDTO.getResponse());
            sb.append("  ");
        }
        return sb.toString();
    }


    // 인성검사 후 인재상 코드를 추출하는 함수
    private String extractCodeFromResultRate(String rate) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CodeMemberResultRateDTO rateDTO = objectMapper.readValue(rate, CodeMemberResultRateDTO.class);
        String result = "";
        result += Double.parseDouble(rateDTO.getN()) > Double.parseDouble(rateDTO.getF()) ? 'N' : 'F';
        result += Double.parseDouble(rateDTO.getD()) > Double.parseDouble(rateDTO.getB()) ? 'D' : 'B';
        result += Double.parseDouble(rateDTO.getC()) > Double.parseDouble(rateDTO.getL()) ? 'C' : 'L';
        result += Double.parseDouble(rateDTO.getS()) > Double.parseDouble(rateDTO.getO()) ? 'S' : 'O';

        return result;
    }


    // 인성검사 후 인재상 코드를 비율을 불러오는 함수
    private Mono<String> callGeminiForRate(String modelData, String codeFileContent) {
        String codePrompt = modelData + "당신은 인재상 분석 전문가입니다. 다음 인재상 질문과 응답, 그리고 각 자리에 해당하는 2가지 코드 정보를 바탕으로 가능한 8가지 인재상 코드 조합과 그 비율을 **순수한 JSON 형식으로만** 생성해 주세요. 다른 설명이나 해석은 필요 없습니다.\n\n인재상 코드 정보:\n1. 소통하는 (N) vs 집중하는 (F)\n2. 주도적인 (D) vs 안정적인 (B)\n3. 창의적인 (C) vs 분석적인 (L)\n4. 수직적인 (S) vs 수평적인 (O)\n\n대립된 코드들을 서로 합하여 100%가 됩니다. 총 8가지의 4자리 인재상 코드와 그 비율을 JSON 형태로 나타내 주세요. 예시는 다음과 같습니다.\n\n```json\n{\n\t\"N\" : \"20.3\",\n\t\"F\" : \"79.7\",\n\t\"D\" : \"30.12\",\n\t\"B\" : \"69.88\",\n\t\"C\" : \"16.11\",\n\t\"L\" : \"83.89\",\n\t\"S\" : \"54.2\",\n\t\"O\" : \"45.8\"\n}\n```\n\n다음 내용을 바탕으로 전달해주세요." + codeFileContent;
        return codeService.callGemini(codePrompt).map(response -> {
            try {
                String jsonString = response.replaceAll("```json", "").replaceAll("```", "").trim();
                jsonString = jsonString.toLowerCase();
                log.info(jsonString);
                return jsonString;
            } catch (Exception e) {
                return response.toLowerCase();
            }
        });
    }

    // 인성검사 후 인재상 상세 설명을 AI로 불러오는 함수
    private Mono<String> callGeminiForDescription(String modelData, String codeFileContent) {
        String descriptionPrompt = modelData + "당신은 인재상 분석 전문가입니다. 제공된 질문과 답변, 그리고 인재상 코드별 상세 특징을 바탕으로 다음을 분석해주세요. \n 1. 이 사람의 업무와 관련된 강점과 약점을 총 3줄 이내로 짧고 부드럽게 정리해주세요. \n 2. 약점은 단정적으로 표현하지 말고, 해당 경향이 가진 강점을 함께 언급하거나, 자연스럽게 보완 방법까지 함께 제시해주세요 \n 3. 코드 이름이나 코드 특성을 직접 언급하지 말고, 직무/성향 중심으로 유추 가능하게 작성해주세요. \n\n" + codeFileContent;
        return codeService.callGemini(descriptionPrompt);
    }

    // 인성검사 후 인재상 적합 여부를 AI로 불러오는 함수
    private Mono<String> callGeminiForSuitability(String modelData, String suitabilityFileContent) {
        String suitabilityPrompt = modelData + "당신은 채용 적합성 평가 전문가입니다. 제공된 질문과 답변, 그리고 관련 정보를 바탕으로 이 지원자가 해당 직무에 '적합'한지 혹은 '부적합'한지 명확하게 판단하여 단어로만 알려주세요. 판단 근거는 내부적으로 활용하며, 외부로 설명할 필요는 없습니다. \n\n" + suitabilityFileContent;
        return codeService.callGemini(suitabilityPrompt);
    }













    // 개인 인성검사 - 검사 결과 API
    @PostMapping("/member/result")
    public Mono<ResponseEntity<CodeMemberResultSuccessResponseDTO>> memberResult(@RequestBody List<CodeMemberResultRequestDTO> requestDTOList) throws Exception {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        String codeFileContent = FileUtil.readResourceFile("code.txt");
        String suitabilityFileContent = FileUtil.readResourceFile("suitability.txt");
        String modelData = fromDtoToString(requestDTOList);

//        Mono<String> codeMono = callGeminiForCode(modelData, codeFileContent);
        Mono<String> descriptionMono = callGeminiForDescription(modelData, codeFileContent);
        Mono<String> suitabilityMono = callGeminiForSuitability(modelData, suitabilityFileContent);
        Mono<String> ratioMono = callGeminiForRate(modelData, codeFileContent);

        List<Mono<String>> monosToZip = Arrays.asList( descriptionMono, suitabilityMono, ratioMono);

        return Mono.zip(monosToZip, results -> {
                    String description = (String) results[0];
                    String suitability = (String) results[1];
                    String ratio = (String) results[2];
                    String code = null;
                    try {
                        code = extractCodeFromResultRate((String) results[2]);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                    suitability = suitability.replaceAll("[\\r\\n]", "");
                    code = code.replaceAll("[\\r\\n]", "");
                    codeService.changeMemberCode(memberNo, suitability, description, code, ratio);
                    return new CodeMemberResultSuccessResponseDTO("Success");
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

    // 개인 인성검사 - 검사 상세 조회 API
    @GetMapping("/member/result")
    public ResponseEntity<?> memberResult() throws JsonProcessingException {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        CodeMemberResultResponseDTO responseDTO = codeService.selectCodeResultByMember(memberNo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //기업 인재상 등록 API
    @PostMapping("/company/register")
    public ResponseEntity<?> companyRegister(@RequestBody CodeCompanyRegisterRequestDTO requestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        CodeCompanyRegisterResponseDTO responseDTO = codeService.companyCodeRegister(memberNo, requestDTO.getCode().getColumData());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //기업 인재상 삭제 API
    @DeleteMapping("/company/delete")
    public ResponseEntity<?> companyDelete() {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        CodeCompanyRegisterResponseDTO responseDTO = codeService.companyCodeRegister(memberNo, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}