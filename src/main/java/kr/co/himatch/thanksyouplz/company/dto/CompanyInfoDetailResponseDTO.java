package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class CompanyInfoDetailResponseDTO {
    private String companyName;
    private String companyManagerName;
    private String companyAddress;
    private String companyPhone;
    private String companyMail;
    private String companyIndustry;
    private String companyEmployee;
    private String companyDescription;
    private String companyLogo;
    private List<CompanyInfoTagResponseDTO> tag;
}