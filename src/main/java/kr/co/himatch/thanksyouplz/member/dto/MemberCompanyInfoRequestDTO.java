package kr.co.himatch.thanksyouplz.member.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MemberCompanyInfoRequestDTO {
    private List<String> companyAddress;
    private List<String> companyPart;
    private List<String> companyType;
}
