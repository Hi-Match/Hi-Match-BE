package kr.co.himatch.thanksyouplz.code.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CodeMemberTimeResponseDTO {
    private LocalDateTime date;
}
