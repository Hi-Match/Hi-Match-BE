package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ResumeAwardDTO {
    private String awaTitle;
    private String awaCompetitionName;
    private String awaOrgan;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime awaDate;
    private String awaContent;

    public ResumeAwardDTO(String awaTitle, String awaCompetitionName, String awaOrgan, LocalDateTime awaDate, String awaContent) {
        this.awaTitle = awaTitle;
        this.awaCompetitionName = awaCompetitionName;
        this.awaOrgan = awaOrgan;
        this.awaDate = awaDate;
        this.awaContent = awaContent;
    }
}