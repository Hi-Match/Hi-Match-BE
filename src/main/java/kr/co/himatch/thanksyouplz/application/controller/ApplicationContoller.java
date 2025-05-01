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

    // 채용 공고 조회
    @GetMapping("/company/select")
    public ResponseEntity<?> companySelect(@RequestParam Long posingNo) {
        ApplicationCompanySelectResponseDTO responseDTO = applicationService.selectJobPosting(posingNo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    // 채용 공고 등록
    @PostMapping("/company/register")
    public ResponseEntity<?> companyRegister(@RequestBody ApplicationCompanyRegisterRequestDTO registerListRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ApplicationCompanyRegisterResponseDTO responseDTO = applicationService.posingRegister(registerListRequestDTO, memberNo);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}