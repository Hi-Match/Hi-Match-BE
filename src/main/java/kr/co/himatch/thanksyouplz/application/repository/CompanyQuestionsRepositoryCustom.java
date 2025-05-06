package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanySelectListResponseDTO;

import java.util.List;

public interface CompanyQuestionsRepositoryCustom {
    // 지원서 자소서 조회
    List<ApplicationCompanySelectListResponseDTO> selectQuestionByPostingNo(Long postingNo);
    // 지원서 자소서 삭제
    void deleteQuestionByPostingNo(Long postingNo);
}
