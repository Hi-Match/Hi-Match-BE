package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.*;

import java.util.List;

public interface ResumeCertificateRepositoryCustom {

    // 이력서 자격증 조회
    List<ResumeCertificateDTO> selectResumeCertificate(Long resumeNo);

    // 이력서 자격증 삭제
    void deleteResumeCertificate(Long resumeNo);

}
