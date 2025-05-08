package kr.co.himatch.thanksyouplz.code.service;

import kr.co.himatch.thanksyouplz.code.dto.CodeCompanyRegisterResponseDTO;
import kr.co.himatch.thanksyouplz.code.dto.CodeMemberQuestionListResponseDTO;
import kr.co.himatch.thanksyouplz.code.dto.CodeMemberTimeResponseDTO;
import kr.co.himatch.thanksyouplz.code.entity.QuestionType;
import kr.co.himatch.thanksyouplz.code.repository.PersonalTestRepository;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import kr.co.himatch.thanksyouplz.company.repository.CompanyRepository;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CodeServiceImpl implements CodeService {

    private final WebClient webClient;
    @Value("${gemini.api-key}")
    private String API_KEY;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PersonalTestRepository personalTestRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public CodeServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    //개인 인성검사 - 검사 결과 API
    @Override
    public Mono<String> callGemini(String prompt) {
        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("key", API_KEY).build())
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                    Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                    return parts.get(0).get("text").toString();
                });
    }

    //개인 인성검사 후, DB에 저장
    @Override
    public void changeMemberCode(Long memberNo, String memberSuitability, String memberDescription, String code) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new NoSuchElementException("Member not found with id: " + memberNo));
        member.changeCodeTestResult(memberSuitability, memberDescription, code);
    }


    //개인 인성검사 - 문제 조회 API - type을 받아서 호출
    @Override
    public List<CodeMemberQuestionListResponseDTO> selectQuestionList(QuestionType questionType) {
        return personalTestRepository.findByPerType(questionType)
                .stream()
                .map(test -> new CodeMemberQuestionListResponseDTO(test.getPerQuestion()))
                .toList();
    }

    //마지막 인성검사 시간 조회
    @Override
    public CodeMemberTimeResponseDTO selectCodeTime(Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);
        CodeMemberTimeResponseDTO responseDTO = new CodeMemberTimeResponseDTO();
        responseDTO.setDate(member.getMemberCodeTime());
        return responseDTO;
    }

    //기업 인재상 등록 API
    @Override
    public CodeCompanyRegisterResponseDTO companyCodeRegister(Long memberNo, String code) {
        Company company = companyRepository.getReferenceById(memberNo);
        company.companyChangeCode(code);

        CodeCompanyRegisterResponseDTO responseDTO = new CodeCompanyRegisterResponseDTO();
        responseDTO.setMessage("Success");
        return responseDTO;
    }
}