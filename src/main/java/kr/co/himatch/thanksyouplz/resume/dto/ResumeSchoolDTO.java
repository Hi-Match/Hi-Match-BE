package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.himatch.thanksyouplz.resume.entity.SchPart;
import kr.co.himatch.thanksyouplz.resume.entity.SchType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Schema(description = "이력서 학력 정보")
@Data
@NoArgsConstructor
@ToString
public class ResumeSchoolDTO {
    @Schema(description = "학교명", example = "OO대학교")
    private String schoolName;
    @Schema(description = "전공", example = "컴퓨터공학과")
    private String schoolMajor;
    @Schema(description = "입학 또는 편입", example = "입학")
    private SchType schoolDegree;
    @Schema(description = "졸업일", example = "20220225", pattern = "yyyyMMdd")
    private LocalDateTime schoolGraduationDate;
    @Schema(description = "입학일", example = "20180302", pattern = "yyyyMMdd")
    private LocalDateTime schoolAdmissionDate;
    @Schema(description = "학점", example = "4.3")
    private BigDecimal schoolGPA;
    @Schema(description = "과정", example = "고등학교")
    private SchPart schoolPart;

    public ResumeSchoolDTO(String schoolName, String schoolMajor, SchType schoolDegree, LocalDateTime schoolGraduationDate, LocalDateTime schoolAdmissionDate, BigDecimal schoolGPA, SchPart schoolPart) {
        this.schoolName = schoolName;
        this.schoolMajor = schoolMajor;
        this.schoolDegree = schoolDegree;
        this.schoolGraduationDate = schoolGraduationDate;
        this.schoolAdmissionDate = schoolAdmissionDate;
        this.schoolGPA = schoolGPA;
        this.schoolPart = schoolPart;
    }
    @JsonProperty("schoolGraduationDate")
    public void setSchoolGraduationDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.schoolGraduationDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.schoolGraduationDate = null;
        }
    }
    @JsonProperty("schoolAdmissionDate")
    public void setSchoolAdmissionDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.schoolAdmissionDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.schoolAdmissionDate = null;
        }
    }
}