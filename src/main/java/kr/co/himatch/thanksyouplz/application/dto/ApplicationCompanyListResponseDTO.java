package kr.co.himatch.thanksyouplz.application.dto;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import kr.co.himatch.thanksyouplz.resume.entity.SchPart;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ApplicationCompanyListResponseDTO {
    private Long applicationNo;
    private LocalDateTime applicationDate;
    private ApplicationStatus applicationStatus;
    private String applicationName;
    private Integer applicationGrade;
    private SchPart applicationPart;

    public ApplicationCompanyListResponseDTO(Long applicationNo, LocalDateTime applicationDate, ApplicationStatus applicationStatus, String applicationName, Integer applicationGrade) {
        this.applicationNo = applicationNo;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
        this.applicationName = applicationName;
        this.applicationGrade = applicationGrade;
    }
}
