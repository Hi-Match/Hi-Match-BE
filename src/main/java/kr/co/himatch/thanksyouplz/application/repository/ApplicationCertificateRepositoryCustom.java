package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailCertificateResponseDTO;

import java.util.List;

public interface ApplicationCertificateRepositoryCustom {
    List<ApplicationCompanyApplyDetailCertificateResponseDTO> selectCertificate(Long applicationNo);
}
