package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyPostingResponseDTO;

import java.util.List;

public interface JobPostingRepositoryCustom {
    List<ApplicationCompanyPostingResponseDTO> selectPostingList(Long memberNo);
}
