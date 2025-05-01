package kr.co.himatch.thanksyouplz.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "채용 공고 질문 리스트")
@Data
@ToString
public class ApplicationCompanySelectListResponseDTO {
    private String question;
    private Integer questionLength;

    public ApplicationCompanySelectListResponseDTO(String question, Integer questionLength) {
        this.question = question;
        this.questionLength = questionLength;
    }
}
