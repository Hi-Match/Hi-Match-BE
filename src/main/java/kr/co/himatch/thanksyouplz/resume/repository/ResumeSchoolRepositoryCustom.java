package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeSchoolDTO;

import java.util.List;

public interface ResumeSchoolRepositoryCustom {

    //이력서 학력 조회
    List<ResumeSchoolDTO> selectResumeSchool(Long resumeNo);
    //이력서 학력 삭제
    void deleteResumeSchool(Long resumeNo);
}
