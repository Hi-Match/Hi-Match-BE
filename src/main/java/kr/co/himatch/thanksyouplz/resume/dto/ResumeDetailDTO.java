package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.himatch.thanksyouplz.resume.entity.ArmyPart;
import kr.co.himatch.thanksyouplz.resume.entity.ArmyType;
import kr.co.himatch.thanksyouplz.resume.entity.Gender;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class ResumeDetailDTO {
    private Long resumeNo;
    private String resumeTitle;
    private String resumeName;
    private String resumeEngName;
    private String resumeMail;
    private String resumeTel;
    private String resumeAddress;
    private String resumeBirthDay;
    private Gender resumeGender;
    private String resumeIMG;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime resumeDate;
    private String resumePortFolio;
    private String resumeAmbition;
    private ArmyType resumeArmyType;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime resumeArmyDate;
    private LocalDateTime resumeArmyEnd;
    @JsonFormat(pattern = "yyyyMMdd")
    private ArmyPart resumeArmyPart;
    private List<ResumeSchoolDTO> resumeSchool;
    private List<ResumeExperienceDTO> resumeExperience;
    private List<ResumeCertificateDTO> resumeCertificate;
    private List<ResumeEducationDTO> resumeEducation;
    private List<ResumeAwardDTO> resumeAward;

    public ResumeDetailDTO(Long resumeNo, String resumeTitle, String resumeName, String resumeEngName, String resumeMail, String resumeTel, String resumeAddress, String resumeBirthDay, Gender resumeGender, String resumeIMG, LocalDateTime resumeDate, String resumePortFolio, String resumeAmbition, ArmyType resumeArmyType, LocalDateTime resumeArmyDate, LocalDateTime resumeArmyEnd, ArmyPart resumeArmyPart) {
        this.resumeNo = resumeNo;
        this.resumeTitle = resumeTitle;
        this.resumeName = resumeName;
        this.resumeEngName = resumeEngName;
        this.resumeMail = resumeMail;
        this.resumeTel = resumeTel;
        this.resumeAddress = resumeAddress;
        this.resumeBirthDay = resumeBirthDay;
        this.resumeGender = resumeGender;
        this.resumeIMG = resumeIMG;
        this.resumeDate = resumeDate;
        this.resumePortFolio = resumePortFolio;
        this.resumeAmbition = resumeAmbition;
        this.resumeArmyType = resumeArmyType;
        this.resumeArmyDate = resumeArmyDate;
        this.resumeArmyEnd = resumeArmyEnd;
        this.resumeArmyPart = resumeArmyPart;
    }
}
