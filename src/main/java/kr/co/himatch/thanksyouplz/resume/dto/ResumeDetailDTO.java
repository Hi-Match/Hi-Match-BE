package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.himatch.thanksyouplz.resume.entity.ArmyPart;
import kr.co.himatch.thanksyouplz.resume.entity.ArmyType;
import kr.co.himatch.thanksyouplz.resume.entity.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Schema(description = "이력서 상세 조회")
@Data
@NoArgsConstructor
@ToString
public class ResumeDetailDTO {
    @Schema(description = "이력서 번호", example = "123")
    private Long resumeNo;
    @Schema(description = "이력서 제목", example = "프론트엔드 개발자 지원")
    private String resumeTitle;
    @Schema(description = "이름", example = "김민수")
    private String resumeName;
    @Schema(description = "영문 이름", example = "Kim Min-soo")
    private String resumeEngName;
    @Schema(description = "이메일", example = "minsoo.kim@example.com")
    private String resumeMail;
    @Schema(description = "연락처", example = "01012345678")
    private String resumeTel;
    @Schema(description = "주소", example = "서울특별시 강남구")
    private String resumeAddress;
    @Schema(description = "생년월일 (YYYYMMDD)", example = "19950315")
    private String resumeBirthDay;
    @Schema(description = "성별(F/M)", example = "M")
    private Gender resumeGender;
    @Schema(description = "이미지 URL", example = "/images/profile.jpg")
    private String resumeIMG;
    @Schema(description = "작성일시", example = "20250421")
    private LocalDateTime resumeDate;
    @Schema(description = "포트폴리오 URL", example = "https://github.com/minsoo")
    private String resumePortFolio;
    @Schema(description = "포부", example = "끊임없이 배우고 성장하는 개발자가 되겠습니다.")
    private String resumeAmbition;
    @Schema(description = "병역 구분", example = "대상")
    private ArmyType resumeArmyType;
    @Schema(description = "입대일", example = "20150601", pattern = "yyyyMMdd")
    private LocalDateTime resumeArmyDate;
    @Schema(description = "전역일", example = "20170531", pattern = "yyyyMMdd")
    private LocalDateTime resumeArmyEnd;
    @Schema(description = "병과", example = "공군")
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


    @JsonProperty("resumeDate")
    public void setResumeDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.resumeDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.resumeDate = null;
        }
    }


    @JsonProperty("resumeArmyDate")
    public void setResumeArmyDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.resumeArmyDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.resumeArmyDate = null;
        }
    }


    @JsonProperty("resumeArmyEnd")
    public void setResumeArmyEnd(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.resumeArmyEnd = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.resumeArmyEnd = null;
        }
    }
}
