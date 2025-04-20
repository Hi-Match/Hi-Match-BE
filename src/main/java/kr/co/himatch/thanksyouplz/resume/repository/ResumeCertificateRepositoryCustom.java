package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.dto.*;

import java.util.List;

public interface ResumeCertificateRepositoryCustom {

    List<ResumeCertificateDTO> selectResumeCertificate(Long resumeNo);

}
