package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompanyChangePassRequestDTO {
    private String memberID;
    private String memberName;
    private String memberPhone;
    private String memberPass;
}
