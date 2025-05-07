package kr.co.himatch.thanksyouplz.code.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CodeMemberResultResponseDTO {
    private String code;
    private String description;

    public CodeMemberResultResponseDTO(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
