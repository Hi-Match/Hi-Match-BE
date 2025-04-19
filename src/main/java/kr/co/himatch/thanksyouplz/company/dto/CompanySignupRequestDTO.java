package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class CompanySignupRequestDTO {
    private String memberID;
    private String memberPass;
    private String memberName;
    private String licenseNumber;
    private String companyName;
    private int companyCount;
    private String companyPart;
    private String memberMail;
    private String memberPhone;
}
