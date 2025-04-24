package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.*;

import java.util.List;

public interface ResumeRepositoryCustom {

    List<ResumeListResponseDTO> selectResumeList(Long memberNo);

    ResumeDetailDTO selectResumeDetail(Long memberNo, Long resumeNo);

    void deleteResumeDetail(Long memberNo, Long resumeNo);

    Long countResumeDetailByMemberAndResume(Long memberNo, Long resumeNo);
    Long countResumeDetailByMember(Long memberNo);

}
