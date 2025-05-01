package kr.co.himatch.thanksyouplz.application.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationMemberQuestionResponseDTO {
    private String question;

    public ApplicationMemberQuestionResponseDTO(String question) {
        this.question = question;
    }
}
