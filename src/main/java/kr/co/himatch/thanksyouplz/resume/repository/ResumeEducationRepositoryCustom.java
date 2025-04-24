package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeEducationDTO;

import java.util.List;

public interface ResumeEducationRepositoryCustom {

    List<ResumeEducationDTO> selectResumeEducation(Long resumeNo);

    void deleteResumeEducation(Long resumeNo);
}
