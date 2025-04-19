package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompanyMemberFindPWRequestDTO {
    private String memberID;
    private String memberName;
    private String memberPhone;
}
