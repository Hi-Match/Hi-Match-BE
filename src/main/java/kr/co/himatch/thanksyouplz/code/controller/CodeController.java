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
        String codePrompt =
                "당신은 인재상 분석 전문가입니다. 아래는 한 응답자의 '질문과 답변 목록'이며, 각 문항은 특정 인재상 코드 쌍(N vs F, D vs B, C vs L, S vs O)에 대응됩니다.\n\n" +

                        "당신의 임무는 이 응답자의 성향을 분석하여, 4가지 인재상 코드 쌍에 대해 각 항목의 비율을 **100% 기준**으로 정확하게 산출하는 것입니다:\n" +
                        "- N (Network, 소통) vs F (Focus, 몰입)\n" +
                        "- D (Drive, 주도) vs B (Balance, 안정)\n" +
                        "- C (Creative, 창의) vs L (Logical, 분석)\n" +
                        "- S (Structure, 수직) vs O (Open, 수평)\n\n" +

                        "**분석 시 반드시 지켜야 할 원칙**:\n" +
                        "- 응답자가 어떤 성향인지 '답변 내용'을 기반으로 판단해야 합니다.\n" +
                        "- 각 문항은 해당 코드에 속하지만, 응답 내용이 긍정이면 해당 코드에 가산하고, 부정이면 반대 코드에 가산해야 합니다.\n" +
                        "- 예: '나는 혼자 일할 때 효율이 높다'가 Focus 코드라면,\n" +
                        "  → '예'라고 답하면 Focus에 가산, '아니오'라고 답하면 Network에 가산\n" +
                        "- '매우 그렇다'는 가장 강한 가중치, '약간 아니다'는 낮은 가중치로 해석하세요.\n" +
                        "- 총합은 반드시 각 쌍별로 100%가 되어야 하며, 소수점 1자리까지 반올림하세요.\n\n" +

                        "**다른 설명이나 텍스트 없이 오직 아래 형식으로 된 JSON만 출력하세요. 아래 이외의 키(`code`, `description`, `slogan` 등)는 절대 포함하지 마세요:**\n\n" +
                        "```json\n" +
                        "{\n" +
                        "  \"N\": \"45.3\",\n" +
                        "  \"F\": \"54.7\",\n" +
                        "  \"D\": \"62.1\",\n" +
                        "  \"B\": \"37.9\",\n" +
                        "  \"C\": \"50.0\",\n" +
                        "  \"L\": \"50.0\",\n" +
                        "  \"S\": \"40.2\",\n" +
                        "  \"O\": \"59.8\"\n" +
                        "}\n" +
                        "```\n\n" +

                        "응답자의 질문과 답변 목록:\n" +
                        modelData + "\n\n" +
                        "질문별 코드 매핑 정보:\n" +
                        codeFileContent;


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
        String suitabilityPrompt = modelData + "적합인지 부적합인지 반드시 하나만 선택해서 알려줘 \n다소 부정적인 문항이 포함되어 있더라도," +
                "자기 성찰, 회복력, 협업 의지가 보인다면 적합으로 판단해줘. \n단순히 부정적 문장이 많다고 무조건 부적합으로 분류하면 안돼." +
                "충분히 분석하고 나서 문장으로 설명하지 말고 '적합'인지 '부적합'인지 판단해줘."
                + suitabilityFileContent;
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

    // 기업 인재상 조회 API
    @GetMapping("/company/select")
    public ResponseEntity<?> companySelect(@RequestParam String code) {
        CodeCompanySelectResponseDTO responseDTO = codeService.selectCodeSelect(code);
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