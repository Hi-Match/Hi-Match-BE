package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailSchoolResponseDTO;
import kr.co.himatch.thanksyouplz.resume.entity.SchPart;

import java.util.List;

public interface ApplicationSchoolRepositoryCustom {
    List<ApplicationCompanyApplyDetailSchoolResponseDTO> selectSchool(Long applicationNo);
    SchPart selectLastEducationPartByApplicationNo(Long applicationNo);
}
