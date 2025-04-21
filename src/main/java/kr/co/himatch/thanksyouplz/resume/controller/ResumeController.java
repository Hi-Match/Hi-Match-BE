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

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        List<ResumeListResponseDTO> resumeList = resumeService.findResumeList(memberNo);
        return new ResponseEntity<>(resumeList, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam Long resumeNo) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ResumeDetailDTO detail = resumeService.findResumeDetail(memberNo, resumeNo);
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

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

    @PutMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody ResumeDetailDTO resumeDetailDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ResumeDetailResponseDTO detail = resumeService.modifyResumeDetail(resumeDetailDTO, memberNo);
        if (detail == null) {
            detail = new ResumeDetailResponseDTO();
            detail.setMessage("본인만 수정할 수 있습니다.");
            return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long resumeNo) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        resumeService.deleteResumeDetail(resumeNo, memberNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
