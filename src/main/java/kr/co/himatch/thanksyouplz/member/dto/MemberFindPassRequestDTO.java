package kr.co.himatch.thanksyouplz.member.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberFindPassRequestDTO {
    private String memberID;
    private String memberName;
    private String memberPhone;
}
