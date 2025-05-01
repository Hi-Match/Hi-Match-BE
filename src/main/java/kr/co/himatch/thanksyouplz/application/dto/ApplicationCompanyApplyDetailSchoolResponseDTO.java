package kr.co.himatch.thanksyouplz.application.dto;

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

@Data
@NoArgsConstructor
@ToString
public class ApplicationCompanyApplyDetailSchoolResponseDTO {
    private String schoolName;
    private String schoolMajor;
    private String schoolMinor;
    private String schoolMultiple;
    private SchType schoolDegree;
    private LocalDateTime schoolGraduationDate;
    private LocalDateTime schoolAdmissionDate;
    private BigDecimal schoolGPA;
    private BigDecimal schoolStandardGPA;
    private SchPart schoolPart;
    private Long schoolLev;

    public ApplicationCompanyApplyDetailSchoolResponseDTO(String schoolName, String schoolMajor, String schoolMinor, String schoolMultiple, SchType schoolDegree, LocalDateTime schoolGraduationDate, LocalDateTime schoolAdmissionDate, BigDecimal schoolGPA, BigDecimal schoolStandardGPA, SchPart schoolPart, Long schoolLev) {
        this.schoolName = schoolName;
        this.schoolMajor = schoolMajor;
        this.schoolMinor = schoolMinor;
        this.schoolMultiple = schoolMultiple;
        this.schoolDegree = schoolDegree;
        this.schoolGraduationDate = schoolGraduationDate;
        this.schoolAdmissionDate = schoolAdmissionDate;
        this.schoolGPA = schoolGPA;
        this.schoolStandardGPA = schoolStandardGPA;
        this.schoolPart = schoolPart;
        this.schoolLev = schoolLev;
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