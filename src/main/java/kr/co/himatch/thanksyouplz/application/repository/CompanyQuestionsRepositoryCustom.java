package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanySelectListResponseDTO;

import java.util.List;

public interface CompanyQuestionsRepositoryCustom {
    List<ApplicationCompanySelectListResponseDTO> selectQuestionByPostingNo(Long postingNo);
    void deleteQuestionByPostingNo(Long postingNo);
}
