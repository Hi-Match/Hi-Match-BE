package kr.co.himatch.thanksyouplz.resume.service;

import kr.co.himatch.thanksyouplz.resume.dto.*;

import java.util.List;

public interface ResumeService {
    // 이력서 목록 조회
    List<ResumeListResponseDTO> findResumeList(Long memberNo);

    // 이력서 상제 조회 API
    ResumeDetailDTO findResumeDetail(Long memberNo, Long resumeNo);

    // 이력서 등록 API
    ResumeDetailResponseDTO registerResumeDetail(ResumeDetailDTO resumeDetailDTO, Long memberNo);

    // 이력서 삭제 API
    ResumeDetailResponseDTO deleteResumeDetail(Long resumeNo, Long memberNo);
}
