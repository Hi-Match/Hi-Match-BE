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
public class ApplicationCompanyApplyDetailCertificateResponseDTO {
    private String cerTitle;
    private String cerAuthority;
    private LocalDateTime cerDate;
    private LocalDateTime cerExpire;

    public ApplicationCompanyApplyDetailCertificateResponseDTO(String cerTitle, String cerAuthority, LocalDateTime cerDate, LocalDateTime cerExpire) {
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