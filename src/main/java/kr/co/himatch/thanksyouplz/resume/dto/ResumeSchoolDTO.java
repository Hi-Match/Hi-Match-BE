package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.himatch.thanksyouplz.resume.entity.SchPart;
import kr.co.himatch.thanksyouplz.resume.entity.SchType;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class ResumeSchoolDTO {
    private String schoolName;
    private String schoolMajor;
    private SchType schoolDegree;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime schoolGraduationDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime schoolAdmissionDate;
    private BigDecimal schoolGPA;
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
}