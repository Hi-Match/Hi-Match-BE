package kr.co.himatch.thanksyouplz.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ResumeEducationDTO {
    private String eduTitle;
    private String eduOrgan;
    private String eduContent;
    private Integer eduTime;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime eduStartDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime eduEndDate;

    public ResumeEducationDTO(String eduTitle, String eduOrgan, String eduContent, Integer eduTime, LocalDateTime eduStartDate, LocalDateTime eduEndDate) {
        this.eduTitle = eduTitle;
        this.eduOrgan = eduOrgan;
        this.eduContent = eduContent;
        this.eduTime = eduTime;
        this.eduStartDate = eduStartDate;
        this.eduEndDate = eduEndDate;
    }
}