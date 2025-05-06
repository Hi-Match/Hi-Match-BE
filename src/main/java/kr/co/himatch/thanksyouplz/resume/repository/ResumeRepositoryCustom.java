package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.*;

import java.util.List;

public interface ResumeRepositoryCustom {

    // 이력서 목록 조회
    List<ResumeListResponseDTO> selectResumeList(Long memberNo);

    // 이력서 상세 조회
    ResumeDetailDTO selectResumeDetail(Long memberNo, Long resumeNo);

    // 이력서 삭제
    void deleteResumeDetail(Long memberNo, Long resumeNo);

    // 이력서 존재여부 확인
    Long countResumeDetailByMemberAndResume(Long memberNo, Long resumeNo);
    
    // 개인별 이력서 수 조회
    Long countResumeDetailByMember(Long memberNo);

}
