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

@Schema(description = "이력서 자격증 정보")
@Data
@NoArgsConstructor
@ToString
public class ResumeCertificateDTO {
    @Schema(description = "자격증 명", example = "정보처리기사")
    private String cerTitle;
    @Schema(description = "발행 기관", example = "한국산업인력공단")
    private String cerAuthority;
    @Schema(description = "취득일", example = "20230510", pattern = "yyyyMMdd")
    private LocalDateTime cerDate;
    @Schema(description = "만료일 (해당 없는 경우 null)", example = "20250510", pattern = "yyyyMMdd")
    private LocalDateTime cerExpire;

    public ResumeCertificateDTO(String cerTitle, String cerAuthority, LocalDateTime cerDate, LocalDateTime cerExpire) {
        this.cerTitle = cerTitle;
        this.cerAuthority = cerAuthority;
        this.cerDate = cerDate;
        this.cerExpire = cerExpire;
    }


    @JsonProperty("cerDate")
    public void setCerDate(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.cerDate = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.cerDate = null;
        }
    }


    @JsonProperty("cerExpire")
    public void setCerExpire(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.cerExpire = LocalDate.parse(dateStr, formatter).atStartOfDay();
        } else {
            this.cerExpire = null;
        }
    }
}