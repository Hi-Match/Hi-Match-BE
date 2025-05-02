package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailEducationResponseDTO;

import java.util.List;

public interface ApplicationEducationRepositoryCustom {
    List<ApplicationCompanyApplyDetailEducationResponseDTO> selectEducation(Long applicationNo);
}
