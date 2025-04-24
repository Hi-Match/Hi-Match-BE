package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeAwardDTO;

import java.util.List;

public interface ResumeAwardRepositoryCustom {
    List<ResumeAwardDTO> selectResumeAward(Long resumeNo);

    void deleteResumeAward(Long resumeNo);
}
