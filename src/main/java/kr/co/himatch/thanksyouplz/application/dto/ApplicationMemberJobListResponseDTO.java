package kr.co.himatch.thanksyouplz.application.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ApplicationMemberJobListResponseDTO {
    private Long postingNo;
    private String companyName;
    private String postingTitle;
    private String companyAddress;
    private String postingEducation;
    private LocalDateTime postingDeadLine;

    public ApplicationMemberJobListResponseDTO(Long postingNo, String companyName, String postingTitle, String companyAddress, String postingEducation, LocalDateTime postingDeadLine) {
        this.postingNo = postingNo;
        this.companyName = companyName;
        this.postingTitle = postingTitle;
        this.companyAddress = companyAddress;
        this.postingEducation = postingEducation;
        this.postingDeadLine = postingDeadLine;
    }
}