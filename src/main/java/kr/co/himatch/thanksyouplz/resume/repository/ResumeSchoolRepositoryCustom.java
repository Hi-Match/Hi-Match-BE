package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeSchoolDTO;

import java.util.List;

public interface ResumeSchoolRepositoryCustom {

    List<ResumeSchoolDTO> selectResumeSchool(Long resumeNo);
    void deleteResumeSchool(Long resumeNo);
}
