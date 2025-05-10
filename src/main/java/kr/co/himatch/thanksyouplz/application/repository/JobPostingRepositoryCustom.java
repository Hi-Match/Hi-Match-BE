package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyPostingResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberJobListResponseDTO;

import java.util.List;

public interface JobPostingRepositoryCustom {
    // 채용 공고 목록 조회
    List<ApplicationCompanyPostingResponseDTO> selectPostingList(Long memberNo);

    // 검색 필터링에 따른 채용 공고 목록 조회
    List<ApplicationMemberJobListResponseDTO> selectPostingBySearch(List<String> address, List<String> part, List<String> type, List<String> education, String keyword, Long page);

    // 검색 필터링에 따른 채용 공고 목록 페이지 수 조회
    Long selectPostingCountBySearch(List<String> address, List<String> part, List<String> type, List<String> education, String keyword);
}
