package kr.co.himatch.thanksyouplz.bookmark.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BookMarkListResponseDTO {
    private Long bookMarkNo;
    private String companyName;
    private String postingTitle;
    private String companyAddress;
    private String postingEducation;
    private LocalDateTime postingDeadLine;

    public BookMarkListResponseDTO(Long bookMarkNo, String companyName, String postingTitle, String companyAddress, String postingEducation, LocalDateTime postingDeadLine) {
        this.bookMarkNo = bookMarkNo;
        this.companyName = companyName;
        this.postingTitle = postingTitle;
        this.companyAddress = companyAddress;
        this.postingEducation = postingEducation;
        this.postingDeadLine = postingDeadLine;
    }
}
