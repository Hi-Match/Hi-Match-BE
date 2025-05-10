package kr.co.himatch.thanksyouplz.code.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CodeMemberResultSuccessResponseDTO {
    private String message;

    public CodeMemberResultSuccessResponseDTO(String message) {
        this.message = message;
    }
}
