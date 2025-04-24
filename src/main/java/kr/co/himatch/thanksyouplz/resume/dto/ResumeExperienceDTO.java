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

@Schema(description = "이력서 경력 정보")
@Data
@NoArgsConstructor
@ToString
public class ResumeExperienceDTO {
    @Schema(description = "회사명", example = "OO 주식회사")
    private String expCompanyName;
    @Schema(description = "직책", example = "소프트웨어 엔지니어")
    private String expPosition;
    @Schema(description = "근무 시작일", example = "20230101", pattern = "yyyyMMdd")
    private LocalDateTime expStartDate;
    @Schema(description = "근무 종료일 (현재 근무 중인 경우 null)", example = "20241231", pattern = "yyyyMMdd")
    private LocalDateTime expEndDate;
    @Schema(description = "담당 업무", example = "백엔드 개발 및 유지보수")
    private String expPart;
    @Schema(description = "주요 성과", example = "API 성능 20% 향상")
    private String expAchievement;
    @Schema(description = "현재 근무 여부", example = "false")
    private Boolean expIsCurrent;

    public ResumeExperienceDTO(String expCompanyName, String expPosition, LocalDateTime expStartDate, LocalDateTime expEndDate, String expPart, String expAchievement, Boolean expIsCurrent) {
        this.expCompanyName = expCompanyName;
        this.expPosition = expPosition;
        this.expStartDate = expStartDate;
        this.expEndDate = expEndDate;
        this.expPart = expPart;
        this.expAchievement = expAchievement;
        this.expIsCurrent = expIsCurrent;
    }
    @JsonProperty("expStartDate")
    public void setExpStartDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.expStartDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.expStartDate = null;
        }
    }
    @JsonProperty("expEndDate")
    public void setExpEndDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.expEndDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.expEndDate = null;
        }
    }
}