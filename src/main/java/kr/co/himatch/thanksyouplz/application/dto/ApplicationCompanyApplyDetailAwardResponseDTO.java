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
public class ApplicationCompanyApplyDetailAwardResponseDTO {
    private String awaTitle;
    private String awaCompetitionName;
    private String awaOrgan;
    private LocalDateTime awaDate;
    private String awaContent;

    public ApplicationCompanyApplyDetailAwardResponseDTO(String awaTitle, String awaCompetitionName, String awaOrgan, LocalDateTime awaDate, String awaContent) {
        this.awaTitle = awaTitle;
        this.awaCompetitionName = awaCompetitionName;
        this.awaOrgan = awaOrgan;
        this.awaDate = awaDate;
        this.awaContent = awaContent;
    }

    @JsonProperty("awaDate")
    public void setAwaDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.awaDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.awaDate = null;
        }
    }
}