package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailAwardResponseDTO;

import java.util.List;

public interface ApplicationAwardRepositoryCustom {
    List<ApplicationCompanyApplyDetailAwardResponseDTO> selectAward(Long applicationNo);
}
