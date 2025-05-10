package kr.co.himatch.thanksyouplz.application.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ApplicationMemberJobListResponseDTO {
    private Long postingNo;
    private Long bookMarkNo;
    private String companyName;
    private String postingTitle;
    private String companyAddress;
    private String companyType;
    private String postingEducation;
    private String companyImgA;
    private LocalDateTime postingDeadLine;

    public ApplicationMemberJobListResponseDTO(Long postingNo, Long bookMarkNo, String companyName, String postingTitle, String companyAddress, String companyType, String postingEducation, String companyImgA, LocalDateTime postingDeadLine) {
        this.postingNo = postingNo;
        this.bookMarkNo = bookMarkNo;
        this.companyName = companyName;
        this.postingTitle = postingTitle;
        this.companyAddress = companyAddress;
        this.companyType = companyType;
        this.postingEducation = postingEducation;
        this.companyImgA = companyImgA;
        this.postingDeadLine = postingDeadLine;
    }

    public ApplicationMemberJobListResponseDTO(Long postingNo, String companyName, String postingTitle, String companyAddress, String companyType, String postingEducation, String companyImgA, LocalDateTime postingDeadLine) {
        this.postingNo = postingNo;
        this.bookMarkNo = null;
        this.companyName = companyName;
        this.postingTitle = postingTitle;
        this.companyAddress = companyAddress;
        this.companyType = companyType;
        this.postingEducation = postingEducation;
        this.companyImgA = companyImgA;
        this.postingDeadLine = postingDeadLine;
    }
}