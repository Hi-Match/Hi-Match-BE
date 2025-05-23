package kr.co.himatch.thanksyouplz.code.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.himatch.thanksyouplz.code.dto.*;
import kr.co.himatch.thanksyouplz.code.entity.QuestionType;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CodeService {
    //개인 인성검사 - 검사 결과 API
    Mono<String> callGemini(String prompt);

    //개인 인성검사 후, DB에 저장
    void changeMemberCode(Long memberNo, String memberSuitability, String memberDescription, String code, String codeRate);

    //기업 인재상 조회 API
    CodeCompanySelectResponseDTO selectCodeSelect(String code);

    //인성검사 질문 리스트 조회
    List<CodeMemberQuestionListResponseDTO> selectQuestionList(QuestionType questionType);

    //마지막 인성검사 시간 조회
    CodeMemberTimeResponseDTO selectCodeTime(Long memberNo);

    //개인의 인성검사 결과 조회
    CodeMemberResultResponseDTO selectCodeResultByMember(Long memberNo) throws JsonProcessingException;

    //기업 인재상 등록 API
    CodeCompanyRegisterResponseDTO companyCodeRegister(Long memberNo, String code);
}
