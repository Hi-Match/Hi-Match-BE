package kr.co.himatch.thanksyouplz.application.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationMemberDetailListResponseDTO {
    private String coverQuestion;
    private String coverContent;

    public ApplicationMemberDetailListResponseDTO(String coverQuestion, String coverContent) {
        this.coverQuestion = coverQuestion;
        this.coverContent = coverContent;
    }
}
