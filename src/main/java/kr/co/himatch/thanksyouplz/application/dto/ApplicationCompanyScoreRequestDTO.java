package kr.co.himatch.thanksyouplz.application.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationCompanyScoreRequestDTO {
    private Long applicationNo;
    private Integer applicationGrade;
}