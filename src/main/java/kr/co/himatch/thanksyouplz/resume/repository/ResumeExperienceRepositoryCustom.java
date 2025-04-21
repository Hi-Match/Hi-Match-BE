package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeExperienceDTO;

import java.util.List;

public interface ResumeExperienceRepositoryCustom {
    List<ResumeExperienceDTO> selectResumeExperience(Long resumeNo);
    void deleteResumeExperience(Long resumeNo);
}
