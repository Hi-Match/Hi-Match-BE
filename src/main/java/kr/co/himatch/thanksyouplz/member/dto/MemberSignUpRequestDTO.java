package kr.co.himatch.thanksyouplz.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Data
@ToString
public class MemberSignUpRequestDTO {
    @NotBlank
    private String memberID;
    @NotBlank
    private String memberPass;
    @NotBlank
    private String memberName;
    private String memberMail;
    @NotBlank
    private String memberPhone;
}
