package kr.co.himatch.thanksyouplz.application.controller;

import kr.co.himatch.thanksyouplz.application.dto.*;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import kr.co.himatch.thanksyouplz.application.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/application")
public class ApplicationContoller {
    @Autowired
    private ApplicationService applicationService;


    // 해당 유저의 지원한 상태에 따른 페이지 수 조회
    @GetMapping("/member/page")
    public ResponseEntity<?> memberPage(@RequestParam ApplicationStatus applicationStatus) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationMemberPageResponseDTO pageResponseDTO = applicationService.selectMaxPage(applicationStatus, memberNo);
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    // 지원자 지원 결과별 COUNT API
    @GetMapping("/member/count")
    public ResponseEntity<?> memberCount() {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationMemberCountResponseDTO countResponseDTO = applicationService.selectCountByStatus(memberNo);
        return new ResponseEntity<>(countResponseDTO, HttpStatus.OK);
    }

    ///himatch/application/member/search
    // 지원자 지원 목록 검색 조회 API
    @PostMapping("/member/search")
    public ResponseEntity<?> memberSearch(@RequestBody ApplicationMemberSearchRequestDTO searchRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationMemberSearchResponseDTO responseDTO = applicationService.selectSearchPageByStatus(searchRequestDTO, memberNo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 지원서 상태에 따른 지원서 조회
    @GetMapping("/member/{applicationStatus}")
    public ResponseEntity<?> memberApplicationStatus(@PathVariable String applicationStatus, @RequestParam Long page) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationStatus status = ApplicationStatus.toUpperCase(applicationStatus);
        List<ApplicationMemberStatusResponseDTO> statusResponseDTOList = applicationService.selectPageByStatus(status, memberNo, page);

        return new ResponseEntity<>(statusResponseDTOList, HttpStatus.OK);
    }

    // 지원서 상세 보기
    @GetMapping("/member/detail")
    public ResponseEntity<?> memberDetail(@RequestParam Long applicationNo) {
        ApplicationMemberDetailResponseDTO detailResponseDTO = applicationService.selectApplicationDetail(applicationNo);
        return new ResponseEntity<>(detailResponseDTO, HttpStatus.OK);
    }

    // 개인 사용자가 1개의 기업에 채용 지원
    @PostMapping("/member/apply")
    public ResponseEntity<?> memberApply(@RequestBody ApplicationMemberApplyRequestDTO applyRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationMemberApplyResponseDTO responseDTO = applicationService.applyPosting(applyRequestDTO, memberNo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 개인 - 채용 목록 page 조회 시 나오는 모든 공고에 대한 목록 및 검색 API
    @PostMapping("/member/job-list")
    public ResponseEntity<?> memberJobList(@RequestBody ApplicationMemberJobListRequestDTO requestDTO) {
        List<ApplicationMemberJobListResponseDTO> list = applicationService.selectJobList(requestDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 개인 - 체용 목록 page 검색 시, 몇 페이지까지 있는지 조회하는 API
    @PostMapping("/member/search-page")
    public ResponseEntity<?> memberSearchPage(@RequestBody ApplicationMemberSearchPageRequestDTO requestDTO) {
        ApplicationMemberSearchPageResponseDTO responseDTO = applicationService.selectSearchPageCount(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //  기업 공고 등록 전, 기업이 요구할 자기소개서 질문지 조회
    @GetMapping("/company/question-list")
    public ResponseEntity<?> companyQuestionList() {
        List<ApplicationCompanyQuestionResponseDTO> responseDTOList = applicationService.selectQuestionList();
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

    // 기업이 등록한 채용 공고 목록 조회
    @GetMapping("/company/posting-list")
    public ResponseEntity<?> companyPostingList() {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        List<ApplicationCompanyPostingResponseDTO> list = applicationService.selectPostingList(memberNo);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 채용 공고 조회
    @GetMapping("/company/select")
    public ResponseEntity<?> companySelect(@RequestParam Long posingNo) {
        ApplicationCompanySelectResponseDTO responseDTO = applicationService.selectJobPosting(posingNo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 채용 공고의 지원자 목록 조회
    @GetMapping("/company/list")
    public ResponseEntity<?> companyList(@RequestParam Long posingNo) {
        List<ApplicationCompanyListResponseDTO> list = applicationService.selectCompanyList(posingNo);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // 채용 공고 등록
    @PostMapping("/company/register")
    public ResponseEntity<?> companyRegister(@RequestBody ApplicationCompanyRegisterRequestDTO registerListRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationCompanyRegisterResponseDTO responseDTO = applicationService.postingRegister(registerListRequestDTO, memberNo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 채용 공고 수정
    @PutMapping("/company/modify")
    public ResponseEntity<?> companyModify(@RequestBody ApplicationCompanyModifyRequestDTO modifyListRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationCompanyModifyResponseDTO modifyResponseDTO = applicationService.postingModify(modifyListRequestDTO, memberNo);
        if (modifyResponseDTO == null) {
            modifyResponseDTO = new ApplicationCompanyModifyResponseDTO();
            modifyResponseDTO.setMessage("본인만 수정할 수 있습니다.");
            return new ResponseEntity<>(modifyResponseDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modifyResponseDTO, HttpStatus.OK);
    }

    // 채용 공고 삭제
    @DeleteMapping("/company/delete")
    public ResponseEntity<?> companyDelete(@RequestParam Long postingNo) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationCompanyDeleteResponseDTO deleteResponseDTO = applicationService.posingDelete(memberNo, postingNo);
        if (deleteResponseDTO == null) {
            deleteResponseDTO = new ApplicationCompanyDeleteResponseDTO();
            deleteResponseDTO.setMessage("본인만 삭제할 수 있습니다.");
            return new ResponseEntity<>(deleteResponseDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deleteResponseDTO, HttpStatus.OK);
    }

    // 기업 - 이력서 상세 조회
    @GetMapping("/company/apply-detail")
    public ResponseEntity<?> companyApplyDetail(@RequestParam Long applicationNo) {
        ApplicationCompanyApplyDetailResponseDTO detailResponseDTO = applicationService.selectApplication(applicationNo);
        return new ResponseEntity<>(detailResponseDTO, HttpStatus.OK);
    }

    // 서류 합격 API
    @PostMapping("/company/resume-pass")
    public ResponseEntity<?> companyResumePass(@RequestBody ApplicationCompanyResumeStatusRequestDTO requestDTO) {
        ApplicationCompanyResumeStatusResponseDTO responseDTO = applicationService.applicationStatusModify(requestDTO.getApplicationNo(), ApplicationStatus.RESUME_PASS);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 최종 합격 API
    @PostMapping("/company/final-pass")
    public ResponseEntity<?> companyFinalPass(@RequestBody ApplicationCompanyResumeStatusRequestDTO requestDTO) {
        ApplicationCompanyResumeStatusResponseDTO responseDTO = applicationService.applicationStatusModify(requestDTO.getApplicationNo(), ApplicationStatus.FINAL_PASS);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 불합격 API
    @PostMapping("/company/personal-fail")
    public ResponseEntity<?> companyPersonalFail(@RequestBody ApplicationCompanyResumeStatusRequestDTO requestDTO) {
        ApplicationCompanyResumeStatusResponseDTO responseDTO = applicationService.applicationStatusModify(requestDTO.getApplicationNo(), ApplicationStatus.FAIL);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 기업 - 지원서 열람 후 점수 입력 API
    @PostMapping("/company/score")
    public ResponseEntity<?> companyScore(@RequestBody ApplicationCompanyScoreRequestDTO requestDTO) {
        ApplicationCompanyScoreResponseDTO responseDTO = applicationService.applicationScoreInput(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 기업 - 조기 마감 API
    @PostMapping("/company/early-finish")
    public ResponseEntity<?> companyEarlyFinish(@RequestBody ApplicationCompanyEarlyFinishRequestDTO requestDTO) {
        ApplicationCompanyEarlyFinishResponseDTO responseDTO = applicationService.applicationEarlyFinish(requestDTO.getPostingNo());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // 기업 - 1개의 지원 타입에 대한 전체 불합격
    @PostMapping("/company/category-fail")
    public ResponseEntity<?> companyCategoryFail(@RequestBody ApplicationCompanyCategoryFailRequestDTO requestDTO) {
        ApplicationCompanyCategoryFailResponseDTO responseDTO = applicationService.applicationCategoryFail(requestDTO.getPostingNo(), requestDTO.getCategory());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}