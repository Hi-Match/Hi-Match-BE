package kr.co.himatch.thanksyouplz.resume.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ResumeListResponseDTO {
    private Long resumeNo;
    private String resumeTitle;
    private LocalDateTime resumeDate;

    public ResumeListResponseDTO(Long resumeNo, String resumeTitle, LocalDateTime resumeDate) {
        this.resumeNo = resumeNo;
        this.resumeTitle = resumeTitle;
        this.resumeDate = resumeDate;
    }
}
