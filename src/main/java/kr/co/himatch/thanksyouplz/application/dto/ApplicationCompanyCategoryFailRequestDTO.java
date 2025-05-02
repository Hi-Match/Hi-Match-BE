package kr.co.himatch.thanksyouplz.application.dto;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationCompanyCategoryFailRequestDTO {
    private Long postingNo;
    private ApplicationStatus category;
}
