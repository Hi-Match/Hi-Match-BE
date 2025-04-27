package kr.co.himatch.thanksyouplz.resume.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.member.repository.MemberRepository;
import kr.co.himatch.thanksyouplz.resume.dto.*;
import kr.co.himatch.thanksyouplz.resume.entity.*;
import kr.co.himatch.thanksyouplz.resume.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private MemberRepository memberRepository;
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
    public ResumeDetailResponseDTO registerResumeDetail(ResumeDetailDTO resumeDetailDTO, Long memberNo) {
        Long count = resumeRepository.countResumeDetailByMember(memberNo);
        if (count >= 3) {
            return null;
        }

        saveResume(resumeDetailDTO, memberNo);

        ResumeDetailResponseDTO resumeDetailResponseDTO = new ResumeDetailResponseDTO();
        resumeDetailResponseDTO.setMessage("Success");
        return resumeDetailResponseDTO;
    }

    @Override
    public ResumeDetailResponseDTO deleteResumeDetail(Long resumeNo, Long memberNo) {
        Long count = resumeRepository.countResumeDetailByMemberAndResume(memberNo, resumeNo);
        if (count < 1) {
            return null;
        }
        resumeAwardRepository.deleteResumeAward(resumeNo);
        resumeCertificateRepository.deleteResumeCertificate(resumeNo);
        resumeEducationRepository.deleteResumeEducation(resumeNo);
        resumeExperienceRepository.deleteResumeExperience(resumeNo);
        resumeSchoolRepository.deleteResumeSchool(resumeNo);
        resumeRepository.deleteResumeDetail(memberNo, resumeNo);

        ResumeDetailResponseDTO resumeDetailResponseDTO = new ResumeDetailResponseDTO();
        resumeDetailResponseDTO.setMessage("Success");
        return resumeDetailResponseDTO;
    }


    void saveResume(ResumeDetailDTO resumeDetailDTO, Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);
        Resume resume = resumeRepository.save(Resume
                .builder()
                .memberNo(member)
                .resumeTitle(resumeDetailDTO.getResumeTitle())
                .resumeName(resumeDetailDTO.getResumeName())
                .resumeEngname(resumeDetailDTO.getResumeEngName())
                .resumeMail(resumeDetailDTO.getResumeMail())
                .resumeTel(resumeDetailDTO.getResumeTel())
                .resumeAddress(resumeDetailDTO.getResumeAddress())
                .resumeBirthday(resumeDetailDTO.getResumeBirthDay())
                .resumeGender(resumeDetailDTO.getResumeGender())
                .resumeImg(resumeDetailDTO.getResumeIMG())
                .resumeDate(resumeDetailDTO.getResumeDate())
                .resumePortfolio(resumeDetailDTO.getResumePortFolio())
                .resumeAmbition(resumeDetailDTO.getResumeAmbition())
                .resumeArmyType(resumeDetailDTO.getResumeArmyType())
                .resumeArmyDate(resumeDetailDTO.getResumeArmyDate())
                .resumeArmyEnd(resumeDetailDTO.getResumeArmyEnd())
                .resumeArmyPart(resumeDetailDTO.getResumeArmyPart())
                .resumeDisability(resumeDetailDTO.getResumeDisability())
                .resumeDisabilityType(resumeDetailDTO.getResumeDisabilityType())
                .resumeRewardingPatriotism(resumeDetailDTO.getResumeRewardingPatriotism())
                .build());

        Optional.ofNullable(resumeDetailDTO.getResumeSchool())
                .orElseGet(List::of)
                .forEach(resumeSchoolDTO -> resumeSchoolRepository.save(ResumeSchool
                        .builder()
                        .resumeNo(resume)
                        .schName(resumeSchoolDTO.getSchoolName())
                        .schMajor(resumeSchoolDTO.getSchoolMajor())
                        .schDegree(resumeSchoolDTO.getSchoolDegree())
                        .schGraduationDate(resumeSchoolDTO.getSchoolGraduationDate())
                        .schAdmissionDate(resumeSchoolDTO.getSchoolAdmissionDate())
                        .schGpa(resumeSchoolDTO.getSchoolGPA())
                        .schPart(resumeSchoolDTO.getSchoolPart())
                        .build()));

        Optional.ofNullable(resumeDetailDTO.getResumeExperience())
                .orElseGet(List::of)
                .forEach(resumeExperienceDTO -> resumeExperienceRepository.save(ResumeExperience
                        .builder()
                        .resumeNo(resume)
                        .expCompanyName(resumeExperienceDTO.getExpCompanyName())
                        .expPosition(resumeExperienceDTO.getExpPosition())
                        .expStartDate(resumeExperienceDTO.getExpStartDate())
                        .expEndDate(resumeExperienceDTO.getExpEndDate())
                        .expPart(resumeExperienceDTO.getExpPart())
                        .expAchievement(resumeExperienceDTO.getExpAchievement())
                        .expIsCurrent(resumeExperienceDTO.getExpIsCurrent())
                        .build()));

        Optional.ofNullable(resumeDetailDTO.getResumeCertificate())
                .orElseGet(List::of)
                .forEach(resumeCertificateDTO -> resumeCertificateRepository.save(ResumeCertificate
                        .builder()
                        .resumeNo(resume)
                        .cerTitle(resumeCertificateDTO.getCerTitle())
                        .cerIssuingAuthority(resumeCertificateDTO.getCerAuthority())
                        .cerDate(resumeCertificateDTO.getCerDate())
                        .cerExpire(resumeCertificateDTO.getCerExpire())
                        .build()));

        Optional.ofNullable(resumeDetailDTO.getResumeEducation())
                .orElseGet(List::of)
                .forEach(resumeEducationDTO -> resumeEducationRepository.save(ResumeEducation
                        .builder()
                        .resumeNo(resume)
                        .eduTitle(resumeEducationDTO.getEduTitle())
                        .eduOrgan(resumeEducationDTO.getEduOrgan())
                        .eduContent(resumeEducationDTO.getEduContent())
                        .eduTime(resumeEducationDTO.getEduTime())
                        .eduStartDate(resumeEducationDTO.getEduStartDate())
                        .eduEndDate(resumeEducationDTO.getEduEndDate())
                        .build()));

        Optional.ofNullable(resumeDetailDTO.getResumeAward())
                .orElseGet(List::of)
                .forEach(resumeAwardDTO -> resumeAwardRepository.save(ResumeAward
                        .builder()
                        .resumeNo(resume)
                        .awaTitle(resumeAwardDTO.getAwaTitle())
                        .awaCompetitionName(resumeAwardDTO.getAwaCompetitionName())
                        .awaOrgan(resumeAwardDTO.getAwaOrgan())
                        .awaDate(resumeAwardDTO.getAwaDate())
                        .awaContent(resumeAwardDTO.getAwaContent())
                        .build()));

    }

}
