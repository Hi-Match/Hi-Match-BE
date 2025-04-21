package kr.co.himatch.thanksyouplz.resume.service;

import kr.co.himatch.thanksyouplz.resume.dto.*;

import java.util.List;

public interface ResumeService {
    List<ResumeListResponseDTO> findResumeList(Long memberNo);

    ResumeDetailDTO findResumeDetail(Long memberNo, Long resumeNo);

    ResumeDetailResponseDTO registerResumeDetail(ResumeDetailDTO resumeDetailDTO, Long memberNo);

    ResumeDetailResponseDTO modifyResumeDetail(ResumeDetailDTO resumeDetailDTO, Long memberNo);

    ResumeDetailResponseDTO deleteResumeDetail(Long resumeNo, Long memberNo);
}
