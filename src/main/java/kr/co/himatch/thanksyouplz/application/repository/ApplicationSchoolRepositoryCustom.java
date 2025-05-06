package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailSchoolResponseDTO;
import kr.co.himatch.thanksyouplz.resume.entity.SchPart;

import java.util.List;

public interface ApplicationSchoolRepositoryCustom {
    // 지원서 학력 조회
    List<ApplicationCompanyApplyDetailSchoolResponseDTO> selectSchool(Long applicationNo);
    // 지원서 최종학력 조회
    SchPart selectLastEducationPartByApplicationNo(Long applicationNo);
}
