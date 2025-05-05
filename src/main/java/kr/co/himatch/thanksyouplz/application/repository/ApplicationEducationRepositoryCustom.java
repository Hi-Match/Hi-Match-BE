package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailEducationResponseDTO;

import java.util.List;

public interface ApplicationEducationRepositoryCustom {
    // 지원서 교육 조회
    List<ApplicationCompanyApplyDetailEducationResponseDTO> selectEducation(Long applicationNo);
}
