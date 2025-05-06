package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyQuestionResponseDTO;

import java.util.List;

public interface CoverLetterCategoryRepositoryCustom {
    // 기업에서 선택할 자기소개서 조회
    List<ApplicationCompanyQuestionResponseDTO> selectQuestionList();
}
