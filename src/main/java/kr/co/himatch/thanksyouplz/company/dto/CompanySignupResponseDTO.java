package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class CompanySignupResponseDTO {
    private String memberID;
    private String companyName;
    private String memberName;
    private String memberJoinDate;
}
