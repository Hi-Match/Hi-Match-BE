package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailSchoolResponseDTO;

import java.util.List;

public interface ApplicationSchoolRepositoryCustom {
    List<ApplicationCompanyApplyDetailSchoolResponseDTO> selectSchool(Long applicationNo);
}
