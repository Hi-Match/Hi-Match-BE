package kr.co.himatch.thanksyouplz.member.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class MemberMyHomeCodeResponseDTO {
    private String slogan;
    private LocalDateTime lastDateTime;
}
