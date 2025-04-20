package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ResumeCertificateDTO {
    private String cerTitle;
    private String cerAuthority;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime cerDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime cerExpire;

    public ResumeCertificateDTO(String cerTitle, String cerAuthority, LocalDateTime cerDate, LocalDateTime cerExpire) {
        this.cerTitle = cerTitle;
        this.cerAuthority = cerAuthority;
        this.cerDate = cerDate;
        this.cerExpire = cerExpire;
    }
}