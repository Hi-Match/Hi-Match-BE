package kr.co.himatch.thanksyouplz.code.service;

import kr.co.himatch.thanksyouplz.code.dto.CodeCompanyRegisterResponseDTO;
import kr.co.himatch.thanksyouplz.code.dto.CodeMemberQuestionListResponseDTO;
import kr.co.himatch.thanksyouplz.code.dto.CodeMemberTimeResponseDTO;
import kr.co.himatch.thanksyouplz.code.entity.QuestionType;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CodeService {
    //개인 인성검사 - 검사 결과 API
    Mono<String> callGemini(String prompt);

    //개인 인성검사 후, DB에 저장
    void changeMemberCode(Long memberNo, String memberSuitability, String memberDescription, String code);

    //인성검사 질문 리스트 조회
    List<CodeMemberQuestionListResponseDTO> selectQuestionList(QuestionType questionType);

    //마지막 인성검사 시간 조회
    CodeMemberTimeResponseDTO selectCodeTime(Long memberNo);

    //기업 인재상 등록 API
    CodeCompanyRegisterResponseDTO companyCodeRegister(Long memberNo, String code);
}
