package kr.co.himatch.thanksyouplz.application.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationCompanyPostingResponseDTO {
    private Long postingNo;
    private String postingTitle;
    private String postingPart;

    public ApplicationCompanyPostingResponseDTO(Long postingNo, String postingTitle, String postingPart) {
        this.postingNo = postingNo;
        this.postingTitle = postingTitle;
        this.postingPart = postingPart;
    }
}
