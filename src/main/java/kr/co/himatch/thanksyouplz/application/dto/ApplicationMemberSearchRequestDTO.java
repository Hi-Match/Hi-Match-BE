package kr.co.himatch.thanksyouplz.application.dto;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class ApplicationMemberSearchRequestDTO {
    private ApplicationStatus category;
    private String keyword;
    private Long page;
}
