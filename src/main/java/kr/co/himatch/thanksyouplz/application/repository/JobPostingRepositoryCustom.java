package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyPostingResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberJobListResponseDTO;

import java.util.List;

public interface JobPostingRepositoryCustom {
    List<ApplicationCompanyPostingResponseDTO> selectPostingList(Long memberNo);

    List<ApplicationMemberJobListResponseDTO> selectPostingBySearch(List<String> address, List<String> part, List<String> type, String keyword, Long page);
    Long selectPostingCountBySearch(List<String> address, List<String> part, List<String> type, String keyword);
}
