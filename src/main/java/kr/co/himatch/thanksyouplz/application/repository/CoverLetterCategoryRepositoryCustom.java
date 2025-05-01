package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberQuestionResponseDTO;

import java.util.List;

public interface CoverLetterCategoryRepositoryCustom {
    List<ApplicationMemberQuestionResponseDTO> selectQuestionList();
}
