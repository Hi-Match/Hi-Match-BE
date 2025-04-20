package kr.co.himatch.thanksyouplz.resume.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.resume.dto.*;
import kr.co.himatch.thanksyouplz.resume.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private ResumeAwardRepository resumeAwardRepository;
    @Autowired
    private ResumeCertificateRepository resumeCertificateRepository;
    @Autowired
    private ResumeEducationRepository resumeEducationRepository;
    @Autowired
    private ResumeExperienceRepository resumeExperienceRepository;
    @Autowired
    private ResumeSchoolRepository resumeSchoolRepository;

    @Override
    public List<ResumeListResponseDTO> findResumeList(Long memberNo) {
        return resumeRepository.selectResumeList(memberNo);
    }

    @Override
    public ResumeDetailDTO findResumeDetail(Long memberNo, Long resumeNo) {
        ResumeDetailDTO detail = resumeRepository.selectResumeDetail(memberNo, resumeNo);
        detail.setResumeAward(resumeAwardRepository.selectResumeAward(resumeNo));
        detail.setResumeCertificate(resumeCertificateRepository.selectResumeCertificate(resumeNo));
        detail.setResumeEducation(resumeEducationRepository.selectResumeEducation(resumeNo));
        detail.setResumeExperience(resumeExperienceRepository.selectResumeExperience(resumeNo));
        detail.setResumeSchool(resumeSchoolRepository.selectResumeSchool(resumeNo));
        return detail;
    }

    @Override
    public ResumeDetailResponseDTO registerResumeDetail(ResumeDetailDTO resumeDetailDTO) {
        return null;
    }

    @Override
    public ResumeDetailResponseDTO modifyResumeDetail(ResumeDetailDTO resumeDetailDTO) {
        return null;
    }

    @Override
    public ResumeDetailResponseDTO deleteResumeDetail(ResumeDetailDTO resumeDetailDTO) {
        return null;
    }

}
