package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompanyMemberFindIDRequestDTO {
    private String memberName;
    private String licenseNumber;
}
