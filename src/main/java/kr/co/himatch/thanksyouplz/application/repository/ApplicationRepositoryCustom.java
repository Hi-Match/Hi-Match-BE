package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyListResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberStatusResponseDTO;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;

import java.util.List;

public interface ApplicationRepositoryCustom {
    // 지원 상태에 따른 페이지 수 구하기
    Long selectMaxPageByApplicationStatus(ApplicationStatus applicationStatus, Long memberNo);

    // 지원 상태에 따른 페이지 수 구하기
    Long selectCountByApplicationStatus(ApplicationStatus applicationStatus, Long memberNo);

    // 지원 모든 상태에 따른 페이지 수 구하기
    Long selectCountByApplicationAllStatus(Long memberNo);

    // 지원 상태에 따른 지원서 구하기
    List<ApplicationMemberStatusResponseDTO> selectPageByStatus(ApplicationStatus applicationStatus, Long memberNo, Long page);

    // 지원자 지원 목록 검색 조회에 따른 페이지수 구하기
    Long selectPageSearchCountByStatus(String keyword, ApplicationStatus applicationStatus, Long memberNo);

    // 지원자 지원 목록 검색 조회
    List<ApplicationMemberStatusResponseDTO> selectPageSearchByStatus(String keyword, ApplicationStatus applicationStatus, Long page, Long memberNo);

    // 채용 공고의 지원자 목록 조회
    List<ApplicationCompanyListResponseDTO> selectCompanyListByPostingNo(Long posingNo);
}
