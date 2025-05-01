package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberDetailListResponseDTO;

import java.util.List;

public interface CoverLetterRepositoryCustom {
    List<ApplicationMemberDetailListResponseDTO> selectQuestionListByApplicationNo(Long applicationNo);
}
