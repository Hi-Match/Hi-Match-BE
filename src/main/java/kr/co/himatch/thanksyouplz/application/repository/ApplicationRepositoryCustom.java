package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;

public interface ApplicationRepositoryCustom {
    // 지원 상태에 따른 페이지 수 구하기
    Long selectMaxPageByApplicationStatus(ApplicationStatus applicationStatus, Long memberNo);
    // 지원 상태에 따른 페이지 수 구하기
    Long selectCountByApplicationStatus(ApplicationStatus applicationStatus, Long memberNo);
    // 지원 모든 상태에 따른 페이지 수 구하기
    Long selectCountByApplicationAllStatus(Long memberNo);
}
