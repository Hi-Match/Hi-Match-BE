package kr.co.himatch.thanksyouplz.application.dto;

import kr.co.himatch.thanksyouplz.resume.entity.ArmyPart;
import kr.co.himatch.thanksyouplz.resume.entity.ArmyType;
import kr.co.himatch.thanksyouplz.resume.entity.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class ApplicationCompanyApplyDetailResponseDTO {
    private Long applicationNo;
    private String applicationTitle;
    private String applicationName;
    private String applicationEngName;
    private String applicationMail;
    private String applicationTel;
    private String applicationAddress;
    private String applicationBirthDay;
    private Gender applicationGender;
    private String applicationIMG;
    private LocalDateTime applicationDate;
    private String applicationPortFolio;
    private String applicationAmbition;
    private ArmyType applicationArmyType;
    private LocalDateTime applicationArmyDate;
    private LocalDateTime applicationArmyEnd;
    private ArmyPart applicationArmyPart;
    private String applicationDisability;
    private String applicationDisabilityType;
    private String applicationRewardingPatriotism;
    private String applicationMemberCode;
    private String applicationMemberSuitability;
    private List<ApplicationMemberDetailListResponseDTO> applicationCover;
    private List<ApplicationCompanyApplyDetailSchoolResponseDTO> applicationSchool;
    private List<ApplicationCompanyApplyDetailExperienceResponseDTO> applicationExperience;
    private List<ApplicationCompanyApplyDetailCertificateResponseDTO> applicationCertificate;
    private List<ApplicationCompanyApplyDetailEducationResponseDTO> applicationEducation;
    private List<ApplicationCompanyApplyDetailAwardResponseDTO> applicationAward;

    public ApplicationCompanyApplyDetailResponseDTO(Long applicationNo, String applicationTitle, String applicationName, String applicationEngName, String applicationMail, String applicationTel, String applicationAddress, String applicationBirthDay, Gender applicationGender, String applicationIMG, LocalDateTime applicationDate, String applicationPortFolio, String applicationAmbition, ArmyType applicationArmyType, LocalDateTime applicationArmyDate, LocalDateTime applicationArmyEnd, ArmyPart applicationArmyPart, String applicationDisability, String applicationDisabilityType, String applicationRewardingPatriotism, String applicationMemberCode, String applicationMemberSuitability) {
        this.applicationNo = applicationNo;
        this.applicationTitle = applicationTitle;
        this.applicationName = applicationName;
        this.applicationEngName = applicationEngName;
        this.applicationMail = applicationMail;
        this.applicationTel = applicationTel;
        this.applicationAddress = applicationAddress;
        this.applicationBirthDay = applicationBirthDay;
        this.applicationGender = applicationGender;
        this.applicationIMG = applicationIMG;
        this.applicationDate = applicationDate;
        this.applicationPortFolio = applicationPortFolio;
        this.applicationAmbition = applicationAmbition;
        this.applicationArmyType = applicationArmyType;
        this.applicationArmyDate = applicationArmyDate;
        this.applicationArmyEnd = applicationArmyEnd;
        this.applicationArmyPart = applicationArmyPart;
        this.applicationDisability = applicationDisability;
        this.applicationDisabilityType = applicationDisabilityType;
        this.applicationRewardingPatriotism = applicationRewardingPatriotism;
        this.applicationMemberCode = applicationMemberCode;
        this.applicationMemberSuitability = applicationMemberSuitability;
    }


}
