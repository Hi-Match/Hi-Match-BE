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
public class ApplicationCompanyApplyDetailEducationResponseDTO {
    private String eduTitle;
    private String eduOrgan;
    private String eduContent;
    private Integer eduTime;
    private LocalDateTime eduStartDate;
    private LocalDateTime eduEndDate;

    public ApplicationCompanyApplyDetailEducationResponseDTO(String eduTitle, String eduOrgan, String eduContent, Integer eduTime, LocalDateTime eduStartDate, LocalDateTime eduEndDate) {
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