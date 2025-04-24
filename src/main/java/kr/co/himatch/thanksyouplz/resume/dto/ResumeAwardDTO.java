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

@Schema(description = "이력서 수상 경력 정보")
@Data
@NoArgsConstructor
@ToString
public class ResumeAwardDTO {
    @Schema(description = "수상 제목", example = "최우수상")
    private String awaTitle;
    @Schema(description = "대회/공모전 명", example = "OO 아이디어 공모전")
    private String awaCompetitionName;
    @Schema(description = "주최/기관", example = "OO 주식회사")
    private String awaOrgan;
    @Schema(description = "수상일", example = "20240815", pattern = "yyyyMMdd")
    private LocalDateTime awaDate;
    @Schema(description = "수상 내용", example = "창의적인 아이디어로 높은 평가를 받음")
    private String awaContent;

    public ResumeAwardDTO(String awaTitle, String awaCompetitionName, String awaOrgan, LocalDateTime awaDate, String awaContent) {
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