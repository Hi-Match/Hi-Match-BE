package kr.co.himatch.thanksyouplz.company.service;

import kr.co.himatch.thanksyouplz.company.dto.CompanyInfoTagResponseDTO;

import java.util.List;

public interface TagService {
    // 모든 태그 조회
    List<CompanyInfoTagResponseDTO> tagList();
}
