package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailAwardResponseDTO;

import java.util.List;

public interface ApplicationAwardRepositoryCustom {
    // 지원서 수상 내역 조회
    List<ApplicationCompanyApplyDetailAwardResponseDTO> selectAward(Long applicationNo);
}
