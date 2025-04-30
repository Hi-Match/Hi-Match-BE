package kr.co.himatch.thanksyouplz.application.controller;

import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberCountResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberPageResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberStatusResponseDTO;
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
}
