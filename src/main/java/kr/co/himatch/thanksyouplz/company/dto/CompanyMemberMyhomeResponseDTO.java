package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CompanyMemberMyhomeResponseDTO {
    private String memberID;
    private String memberMail;
    private String memberName;
    private String memberPhone;
    private LocalDateTime memberJoinDate;
}
