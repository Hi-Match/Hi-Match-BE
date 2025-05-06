package kr.co.himatch.thanksyouplz.resume.controller;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeDetailDTO;
import kr.co.himatch.thanksyouplz.resume.dto.ResumeDetailResponseDTO;
import kr.co.himatch.thanksyouplz.resume.dto.ResumeListResponseDTO;
import kr.co.himatch.thanksyouplz.resume.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    // 이력서 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        List<ResumeListResponseDTO> resumeList = resumeService.findResumeList(memberNo);
        return new ResponseEntity<>(resumeList, HttpStatus.OK);
    }

    // 이력서 상세 조회
    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam Long resumeNo) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ResumeDetailDTO detail = resumeService.findResumeDetail(memberNo, resumeNo);
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    // 이력서 등록
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ResumeDetailDTO resumeDetailDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ResumeDetailResponseDTO detail = resumeService.registerResumeDetail(resumeDetailDTO, memberNo);
        if (detail == null) {
            detail = new ResumeDetailResponseDTO();
            detail.setMessage("이력서를 3개 이상 작성할 수 없습니다.");
            return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    // 이력서 수정
    @PutMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody ResumeDetailDTO resumeDetailDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ResumeDetailResponseDTO detail = resumeService.deleteResumeDetail(resumeDetailDTO.getResumeNo(), memberNo);
        if (detail == null) {
            detail = new ResumeDetailResponseDTO();
            detail.setMessage("본인만 수정할 수 있습니다.");
            return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
        }
        resumeService.registerResumeDetail(resumeDetailDTO, memberNo);
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    // 이력서 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long resumeNo) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ResumeDetailResponseDTO detail = resumeService.deleteResumeDetail(resumeNo, memberNo);
        if (detail == null) {
            detail = new ResumeDetailResponseDTO();
            detail.setMessage("본인만 삭제할 수 있습니다.");
            return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

}
