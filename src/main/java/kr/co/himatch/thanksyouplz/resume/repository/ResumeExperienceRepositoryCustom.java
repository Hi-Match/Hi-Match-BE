package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeExperienceDTO;

import java.util.List;

public interface ResumeExperienceRepositoryCustom {
    // 이력서 경력 조회
    List<ResumeExperienceDTO> selectResumeExperience(Long resumeNo);
    // 이력서 경력 삭제
    void deleteResumeExperience(Long resumeNo);
}
