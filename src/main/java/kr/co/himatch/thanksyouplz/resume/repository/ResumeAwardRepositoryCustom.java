package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.ResumeAwardDTO;

import java.util.List;

public interface ResumeAwardRepositoryCustom {
    // 이력서 수상 내역 조회
    List<ResumeAwardDTO> selectResumeAward(Long resumeNo);

    // 이력서 수상 내역 등록
    void deleteResumeAward(Long resumeNo);
}
