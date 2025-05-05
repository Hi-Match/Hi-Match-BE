package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailCertificateResponseDTO;

import java.util.List;

public interface ApplicationCertificateRepositoryCustom {
    // 지원서 자격증 조회
    List<ApplicationCompanyApplyDetailCertificateResponseDTO> selectCertificate(Long applicationNo);
}
