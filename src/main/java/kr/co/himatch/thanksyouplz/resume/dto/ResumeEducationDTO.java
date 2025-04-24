package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Schema(description = "이력서 교육 이수 정보")
@Data
@NoArgsConstructor
@ToString
public class ResumeEducationDTO {
    @Schema(description = "교육 과정 명", example = "Java Spring Framework 개발 과정")
    private String eduTitle;
    @Schema(description = "교육 기관", example = "OO 개발 학원")
    private String eduOrgan;
    @Schema(description = "교육 내용", example = "Spring Boot, JPA, REST API 개발 학습")
    private String eduContent;
    @Schema(description = "교육 시간 (단위: 시간)", example = "80")
    private Integer eduTime;
    @Schema(description = "교육 시작일", example = "20250301", pattern = "yyyyMMdd")
    private LocalDateTime eduStartDate;
    @Schema(description = "교육 종료일", example = "20250430", pattern = "yyyyMMdd")
    private LocalDateTime eduEndDate;

    public ResumeEducationDTO(String eduTitle, String eduOrgan, String eduContent, Integer eduTime, LocalDateTime eduStartDate, LocalDateTime eduEndDate) {
        this.eduTitle = eduTitle;
        this.eduOrgan = eduOrgan;
        this.eduContent = eduContent;
        this.eduTime = eduTime;
        this.eduStartDate = eduStartDate;
        this.eduEndDate = eduEndDate;
    }
    @JsonProperty("eduStartDate")
    public void setEduStartDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.eduStartDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.eduStartDate = null;
        }
    }
    @JsonProperty("eduEndDate")
    public void setEduEndDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.eduEndDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.eduEndDate = null;
        }
    }
}