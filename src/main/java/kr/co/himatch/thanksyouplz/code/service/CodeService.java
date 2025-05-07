package kr.co.himatch.thanksyouplz.code.service;

import kr.co.himatch.thanksyouplz.code.dto.CodeMemberResultRequestDTO;
import kr.co.himatch.thanksyouplz.code.dto.CodeMemberResultResponseDTO;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface CodeService {
    //개인 인성검사 - 검사 결과 API
    Mono<String> callGemini(String prompt);
    //개인 인성검사 후, DB에 저장
    void changeMemberCode(Long memberNo,String memberSuitability,String memberDescription, String code);
}
