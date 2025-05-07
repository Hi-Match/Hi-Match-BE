package kr.co.himatch.thanksyouplz.code.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CodeMemberQuestionListResponseDTO {
    private String question;

    public CodeMemberQuestionListResponseDTO(String question) {
        this.question = question;
    }
}
