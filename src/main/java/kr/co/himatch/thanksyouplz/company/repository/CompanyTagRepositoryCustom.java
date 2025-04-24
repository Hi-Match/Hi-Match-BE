package kr.co.himatch.thanksyouplz.company.repository;

import kr.co.himatch.thanksyouplz.company.dto.CompanyInfoTagResponseDTO;

import java.util.List;

public interface CompanyTagRepositoryCustom {
    List<CompanyInfoTagResponseDTO> selectCompanyTags(Long memberNo);
    void deleteCompanyTags(Long memberNo);
}
