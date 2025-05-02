package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyQuestionResponseDTO;

import java.util.List;

public interface CoverLetterCategoryRepositoryCustom {
    List<ApplicationCompanyQuestionResponseDTO> selectQuestionList();
}
