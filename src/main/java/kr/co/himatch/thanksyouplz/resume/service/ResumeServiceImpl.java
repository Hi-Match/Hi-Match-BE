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
                .build());

        for (ResumeSchoolDTO resumeSchoolDTO : resumeDetailDTO.getResumeSchool()) {
            resumeSchoolRepository.save(ResumeSchool
                    .builder()
                    .resumeNo(resume)
                    .schName(resumeSchoolDTO.getSchoolName())
                    .schMajor(resumeSchoolDTO.getSchoolMajor())
                    .schDegree(resumeSchoolDTO.getSchoolDegree())
                    .schGraduationDate(resumeSchoolDTO.getSchoolGraduationDate())
                    .schAdmissionDate(resumeSchoolDTO.getSchoolAdmissionDate())
                    .schGpa(resumeSchoolDTO.getSchoolGPA())
                    .schPart(resumeSchoolDTO.getSchoolPart())
                    .build());
        }
        for (ResumeExperienceDTO resumeExperienceDTO : resumeDetailDTO.getResumeExperience()) {
            resumeExperienceRepository.save(ResumeExperience
                    .builder()
                    .resumeNo(resume)
                    .expCompanyName(resumeExperienceDTO.getExpCompanyName())
                    .expPosition(resumeExperienceDTO.getExpPosition())
                    .expStartDate(resumeExperienceDTO.getExpStartDate())
                    .expEndDate(resumeExperienceDTO.getExpEndDate())
                    .expPart(resumeExperienceDTO.getExpPart())
                    .expAchievement(resumeExperienceDTO.getExpAchievement())
                    .expIsCurrent(resumeExperienceDTO.getExpIsCurrent())
                    .build());
        }
        for (ResumeCertificateDTO resumeCertificateDTO : resumeDetailDTO.getResumeCertificate()) {
            resumeCertificateRepository.save(ResumeCertificate
                    .builder()
                    .resumeNo(resume)
                    .cerTitle(resumeCertificateDTO.getCerTitle())
                    .cerIssuingAuthority(resumeCertificateDTO.getCerAuthority())
                    .cerDate(resumeCertificateDTO.getCerDate())
                    .cerExpire(resumeCertificateDTO.getCerExpire())
                    .build());
        }
        for (ResumeEducationDTO resumeEducationDTO : resumeDetailDTO.getResumeEducation()) {
            resumeEducationRepository.save(ResumeEducation
                    .builder()
                    .resumeNo(resume)
                    .eduTitle(resumeEducationDTO.getEduTitle())
                    .eduOrgan(resumeEducationDTO.getEduOrgan())
                    .eduContent(resumeEducationDTO.getEduContent())
                    .eduTime(resumeEducationDTO.getEduTime())
                    .eduStartDate(resumeEducationDTO.getEduStartDate())
                    .eduEndDate(resumeEducationDTO.getEduEndDate())
                    .build());
        }
        for (ResumeAwardDTO resumeAwardDTO : resumeDetailDTO.getResumeAward()) {
            resumeAwardRepository.save(ResumeAward
                    .builder()
                    .resumeNo(resume)
                    .awaTitle(resumeAwardDTO.getAwaTitle())
                    .awaCompetitionName(resumeAwardDTO.getAwaCompetitionName())
                    .awaOrgan(resumeAwardDTO.getAwaOrgan())
                    .awaDate(resumeAwardDTO.getAwaDate())
                    .awaContent(resumeAwardDTO.getAwaContent())
                    .build());
        }

        ResumeDetailResponseDTO resumeDetailResponseDTO = new ResumeDetailResponseDTO();
        resumeDetailResponseDTO.setMessage("Success");
        return resumeDetailResponseDTO;
    }

    @Override
    public ResumeDetailResponseDTO modifyResumeDetail(ResumeDetailDTO resumeDetailDTO, Long memberNo) {
        Long count = resumeRepository.countResumeDetailByMemberAndResume(resumeDetailDTO.getResumeNo(), memberNo);
        if (count < 1) {
            return null;
        }
        resumeAwardRepository.deleteResumeAward(resumeDetailDTO.getResumeNo());
        resumeCertificateRepository.deleteResumeCertificate(resumeDetailDTO.getResumeNo());
        resumeEducationRepository.deleteResumeEducation(resumeDetailDTO.getResumeNo());
        resumeExperienceRepository.deleteResumeExperience(resumeDetailDTO.getResumeNo());
        resumeSchoolRepository.deleteResumeSchool(resumeDetailDTO.getResumeNo());
        resumeRepository.deleteResumeDetail(memberNo, resumeDetailDTO.getResumeNo());
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
                .build());

        for (ResumeSchoolDTO resumeSchoolDTO : resumeDetailDTO.getResumeSchool()) {
            resumeSchoolRepository.save(ResumeSchool
                    .builder()
                    .resumeNo(resume)
                    .schName(resumeSchoolDTO.getSchoolName())
                    .schMajor(resumeSchoolDTO.getSchoolMajor())
                    .schDegree(resumeSchoolDTO.getSchoolDegree())
                    .schGraduationDate(resumeSchoolDTO.getSchoolGraduationDate())
                    .schAdmissionDate(resumeSchoolDTO.getSchoolAdmissionDate())
                    .schGpa(resumeSchoolDTO.getSchoolGPA())
                    .schPart(resumeSchoolDTO.getSchoolPart())
                    .build());
        }
        for (ResumeExperienceDTO resumeExperienceDTO : resumeDetailDTO.getResumeExperience()) {
            resumeExperienceRepository.save(ResumeExperience
                    .builder()
                    .resumeNo(resume)
                    .expCompanyName(resumeExperienceDTO.getExpCompanyName())
                    .expPosition(resumeExperienceDTO.getExpPosition())
                    .expStartDate(resumeExperienceDTO.getExpStartDate())
                    .expEndDate(resumeExperienceDTO.getExpEndDate())
                    .expPart(resumeExperienceDTO.getExpPart())
                    .expAchievement(resumeExperienceDTO.getExpAchievement())
                    .expIsCurrent(resumeExperienceDTO.getExpIsCurrent())
                    .build());
        }
        for (ResumeCertificateDTO resumeCertificateDTO : resumeDetailDTO.getResumeCertificate()) {
            resumeCertificateRepository.save(ResumeCertificate
                    .builder()
                    .resumeNo(resume)
                    .cerTitle(resumeCertificateDTO.getCerTitle())
                    .cerIssuingAuthority(resumeCertificateDTO.getCerAuthority())
                    .cerDate(resumeCertificateDTO.getCerDate())
                    .cerExpire(resumeCertificateDTO.getCerExpire())
                    .build());
        }
        for (ResumeEducationDTO resumeEducationDTO : resumeDetailDTO.getResumeEducation()) {
            resumeEducationRepository.save(ResumeEducation
                    .builder()
                    .resumeNo(resume)
                    .eduTitle(resumeEducationDTO.getEduTitle())
                    .eduOrgan(resumeEducationDTO.getEduOrgan())
                    .eduContent(resumeEducationDTO.getEduContent())
                    .eduTime(resumeEducationDTO.getEduTime())
                    .eduStartDate(resumeEducationDTO.getEduStartDate())
                    .eduEndDate(resumeEducationDTO.getEduEndDate())
                    .build());
        }
        for (ResumeAwardDTO resumeAwardDTO : resumeDetailDTO.getResumeAward()) {
            resumeAwardRepository.save(ResumeAward
                    .builder()
                    .resumeNo(resume)
                    .awaTitle(resumeAwardDTO.getAwaTitle())
                    .awaCompetitionName(resumeAwardDTO.getAwaCompetitionName())
                    .awaOrgan(resumeAwardDTO.getAwaOrgan())
                    .awaDate(resumeAwardDTO.getAwaDate())
                    .awaContent(resumeAwardDTO.getAwaContent())
                    .build());
        }

        ResumeDetailResponseDTO resumeDetailResponseDTO = new ResumeDetailResponseDTO();
        resumeDetailResponseDTO.setMessage("Success");
        return resumeDetailResponseDTO;
    }

    @Override
    public ResumeDetailResponseDTO deleteResumeDetail(Long resumeNo, Long memberNo) {
        Long count = resumeRepository.countResumeDetailByMemberAndResume(memberNo, memberNo);
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

}
