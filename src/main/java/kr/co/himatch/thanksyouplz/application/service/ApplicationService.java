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

    // 기업이 등록한 채용 공고 목록 조회
    List<ApplicationCompanyPostingResponseDTO> selectPostingList(Long memberNo);

    // 채용 공고 조회
    ApplicationCompanySelectResponseDTO selectJobPosting(Long postingNo);

    // 채용 공고 등록
    ApplicationCompanyRegisterResponseDTO postingRegister(ApplicationCompanyRegisterRequestDTO registerRequestDTO, Long memberNo);

    // 채용 공고 수정
    ApplicationCompanyModifyResponseDTO postingModify(ApplicationCompanyModifyRequestDTO modifyRequestDTO, Long memberNo);

    // 채용 공고 삭제
    ApplicationCompanyDeleteResponseDTO posingDelete(Long memberNo, Long postingNo);
}
