package kr.co.himatch.thanksyouplz.application.service;

import kr.co.himatch.thanksyouplz.application.dto.*;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;

import java.util.List;

public interface ApplicationService {
    // 지원서 상태에 따른 max page
    ApplicationMemberPageResponseDTO selectMaxPage(ApplicationStatus applicationStatus, Long memberNo);

    // 지원서 상태에 따른 count
    ApplicationMemberCountResponseDTO selectCountByStatus(Long memberNo);

    // 지원서 상태에 따른 지원서 조회
    List<ApplicationMemberStatusResponseDTO> selectPageByStatus(ApplicationStatus applicationStatus, Long memberNo, Long page);

    // 지원서 상세 보기
    ApplicationMemberDetailResponseDTO selectApplicationDetail(Long applicationNo);

    // 채용 공고 조회
    ApplicationCompanySelectResponseDTO selectJobPosting(Long postingNo);

    // 채용 공고 등록
    ApplicationCompanyRegisterResponseDTO posingRegister(ApplicationCompanyRegisterRequestDTO registerRequestDTO, Long memberNo);
}
