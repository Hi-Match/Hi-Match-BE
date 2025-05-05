package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailExperienceResponseDTO;

import java.util.List;

public interface ApplicationExperienceRepositoryCustom {
    // 지원서 경력 조회
    List<ApplicationCompanyApplyDetailExperienceResponseDTO> selectExperience(Long applicationNo);
}
