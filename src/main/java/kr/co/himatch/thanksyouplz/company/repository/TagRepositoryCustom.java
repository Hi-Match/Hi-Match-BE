package kr.co.himatch.thanksyouplz.company.repository;

import kr.co.himatch.thanksyouplz.company.dto.CompanyInfoTagResponseDTO;

import java.util.List;

public interface TagRepositoryCustom{
    List<CompanyInfoTagResponseDTO> selectTags();
}
