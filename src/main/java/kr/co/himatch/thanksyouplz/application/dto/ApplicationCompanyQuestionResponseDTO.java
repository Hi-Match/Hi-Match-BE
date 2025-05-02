package kr.co.himatch.thanksyouplz.application.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationCompanyQuestionResponseDTO {
    private String question;

    public ApplicationCompanyQuestionResponseDTO(String question) {
        this.question = question;
    }
}
