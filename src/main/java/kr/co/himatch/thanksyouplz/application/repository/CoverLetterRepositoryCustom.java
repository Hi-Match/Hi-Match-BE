package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberDetailListResponseDTO;

import java.util.List;

public interface CoverLetterRepositoryCustom {
    // 지원서 번호로 자기소개서 작성글 조회 
    List<ApplicationMemberDetailListResponseDTO> selectQuestionListByApplicationNo(Long applicationNo);
}
