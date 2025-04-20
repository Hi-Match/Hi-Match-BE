package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ResumeExperienceDTO {
    private String expCompanyName;
    private String expPosition;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime expStartDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime expEndDate;
    private String expPart;
    private String expAchievement;
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
}