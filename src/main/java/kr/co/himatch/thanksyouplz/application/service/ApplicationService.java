package kr.co.himatch.thanksyouplz.application.service;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberCountResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberPageResponseDTO;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;

public interface ApplicationService {
    // 지원서 상태에 따른 max page
    ApplicationMemberPageResponseDTO selectMaxPage(ApplicationStatus applicationStatus, Long memberNo);
    // 지원서 상태에 따른 count
    ApplicationMemberCountResponseDTO selectCountByStatus(Long memberNo);

}
