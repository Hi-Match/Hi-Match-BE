package kr.co.himatch.thanksyouplz.application.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ApplicationMemberJobListRequestDTO {
    private List<String> companyAddress;
    private List<String> companyPart;
    private List<String> companyType;
    private List<String> postingEducation;
    private String keyword;
    private Long page;
}
