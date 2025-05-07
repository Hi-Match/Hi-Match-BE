package kr.co.himatch.thanksyouplz.code.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CodeMemberResultRequestDTO {
    private String question;
    private String response;
}
