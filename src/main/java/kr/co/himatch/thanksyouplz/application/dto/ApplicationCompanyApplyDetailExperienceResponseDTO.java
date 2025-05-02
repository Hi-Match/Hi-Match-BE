package kr.co.himatch.thanksyouplz.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@ToString
public class ApplicationCompanyApplyDetailExperienceResponseDTO {
    private String expCompanyName;
    private String expPosition;
    private LocalDateTime expStartDate;
    private LocalDateTime expEndDate;
    private String expPart;
    private String expAchievement;
    private Boolean expIsCurrent;

    public ApplicationCompanyApplyDetailExperienceResponseDTO(String expCompanyName, String expPosition, LocalDateTime expStartDate, LocalDateTime expEndDate, String expPart, String expAchievement, Boolean expIsCurrent) {
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