package kr.co.himatch.thanksyouplz.bookmark.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BookMarkListResponseDTO {
    private Long bookMarkNo;
    private Long postingNo;
    private String companyImgA;
    private String companyName;
    private String companyType;
    private String postingTitle;
    private String companyAddress;
    private String postingEducation;
    private LocalDateTime postingDeadLine;

    public BookMarkListResponseDTO(Long bookMarkNo, Long postingNo, String companyImgA, String companyName, String companyType, String postingTitle, String companyAddress, String postingEducation, LocalDateTime postingDeadLine) {
        this.bookMarkNo = bookMarkNo;
        this.postingNo = postingNo;
        this.companyImgA = companyImgA;
        this.companyName = companyName;
        this.companyType = companyType;
        this.postingTitle = postingTitle;
        this.companyAddress = companyAddress;
        this.postingEducation = postingEducation;
        this.postingDeadLine = postingDeadLine;
    }
}
