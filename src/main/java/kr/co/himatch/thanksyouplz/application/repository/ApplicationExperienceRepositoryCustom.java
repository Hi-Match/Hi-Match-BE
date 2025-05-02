package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailExperienceResponseDTO;

import java.util.List;

public interface ApplicationExperienceRepositoryCustom {
    List<ApplicationCompanyApplyDetailExperienceResponseDTO> selectExperience(Long applicationNo);
}
