package kr.co.himatch.thanksyouplz.application.dto;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import lombok.Data;
import lombok.ToString;
import org.apache.catalina.core.ApplicationPart;

import java.time.LocalDateTime;

@Data
@ToString
public class ApplicationMemberStatusResponseDTO {
    private Long applicationNo;
    private String applicationName;
    private String applicationPart;
    private String applicationContract;
    private LocalDateTime applicationDate;
    private ApplicationStatus applicationStatus;

    public ApplicationMemberStatusResponseDTO(Long applicationNo, String applicationName, String applicationPart, String applicationContract, LocalDateTime applicationDate, ApplicationStatus applicationStatus) {
        this.applicationNo = applicationNo;
        this.applicationName = applicationName;
        this.applicationPart = applicationPart;
        this.applicationContract = applicationContract;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
    }
}
