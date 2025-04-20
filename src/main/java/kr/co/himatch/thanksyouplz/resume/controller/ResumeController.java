package kr.co.himatch.thanksyouplz.resume.controller;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeDetailDTO;
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
        return null;
    }

}
