package kr.co.himatch.thanksyouplz.application.service;

import kr.co.himatch.thanksyouplz.application.dto.*;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;

import java.util.List;

public interface ApplicationService {
    // 지원서 상태에 따른 max page
    ApplicationMemberPageResponseDTO selectMaxPage(ApplicationStatus applicationStatus, Long memberNo);

    // 지원서 상태에 따른 count
    ApplicationMemberCountResponseDTO selectCountByStatus(Long memberNo);

    // 지원자 지원 목록 검색 조회 API
    ApplicationMemberSearchResponseDTO selectSearchPageByStatus(ApplicationMemberSearchRequestDTO requestDTO, Long memberNo);

    // 지원서 상태에 따른 지원서 조회
    List<ApplicationMemberStatusResponseDTO> selectPageByStatus(ApplicationStatus applicationStatus, Long memberNo, Long page);

    // 기업 공고 등록 전, 기업이 요구할 자기소개서 질문지 조회
    List<ApplicationCompanyQuestionResponseDTO> selectQuestionList();

    // 지원서 상세 보기
    ApplicationMemberDetailResponseDTO selectApplicationDetail(Long applicationNo);

    // 개인 사용자가 1개의 기업에 채용 지원
    ApplicationMemberApplyResponseDTO applyPosting(ApplicationMemberApplyRequestDTO requestDTO, Long memberNo);

    // 기업이 등록한 채용 공고 목록 조회
    List<ApplicationCompanyPostingResponseDTO> selectPostingList(Long memberNo);

    // 채용 공고 조회
    ApplicationCompanySelectResponseDTO selectJobPosting(Long postingNo);

    // 채용 공고의 지원자 목록 조회
    List<ApplicationCompanyListResponseDTO> selectCompanyList(Long postingNo);

    // 채용 공고 등록
    ApplicationCompanyRegisterResponseDTO postingRegister(ApplicationCompanyRegisterRequestDTO registerRequestDTO, Long memberNo);

    // 채용 공고 수정
    ApplicationCompanyModifyResponseDTO postingModify(ApplicationCompanyModifyRequestDTO modifyRequestDTO, Long memberNo);

    // 채용 공고 삭제
    ApplicationCompanyDeleteResponseDTO posingDelete(Long memberNo, Long postingNo);

    // 기업 - 이력서 상세 조회
    ApplicationCompanyApplyDetailResponseDTO selectApplication(Long applicationNo);

    // 기업 - 이력서 서류 상태 변경
    ApplicationCompanyResumeStatusResponseDTO applicationStatusModify(Long applicationNo, ApplicationStatus status);

    // 기업 - 지원서 열람 후 점수 입력 API
    ApplicationCompanyScoreResponseDTO applicationScoreInput(ApplicationCompanyScoreRequestDTO requestDTO);

    // 기업 - 조기 마감 API
    ApplicationCompanyEarlyFinishResponseDTO applicationEarlyFinish(Long postingNo);

    // 기업 - 1개의 지원 타입에 대한 전체 불합격
    ApplicationCompanyCategoryFailResponseDTO applicationCategoryFail(Long postingNo, ApplicationStatus status);

    // 개인 - 채용 목록 page 조회 시 나오는 모든 공고에 대한 목록 및 검색 API
    List<ApplicationMemberJobListResponseDTO> selectJobList(ApplicationMemberJobListRequestDTO requestDTO);

    // 개인 - 체용 목록 page 검색 시, 몇 페이지까지 있는지 조회하는 API
    ApplicationMemberSearchPageResponseDTO selectSearchPageCount(ApplicationMemberSearchPageRequestDTO requestDTO);
}
