package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompanyMemberLoginRequestDTO {
    private String memberID;
    private String memberPass;
}
