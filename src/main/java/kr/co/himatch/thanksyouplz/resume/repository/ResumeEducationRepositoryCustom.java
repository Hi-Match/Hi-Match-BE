package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeEducationDTO;

import java.util.List;

public interface ResumeEducationRepositoryCustom {

    // 이력서 교욱 조회
    List<ResumeEducationDTO> selectResumeEducation(Long resumeNo);

    // 이력서 교욱 삭제
    void deleteResumeEducation(Long resumeNo);
}
