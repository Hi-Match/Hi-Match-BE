package kr.co.himatch.thanksyouplz.member.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberChangePassRequestDTO {
    private String memberID;
    private String memberName;
    private String memberPass;
    private String memberPhone;
}
