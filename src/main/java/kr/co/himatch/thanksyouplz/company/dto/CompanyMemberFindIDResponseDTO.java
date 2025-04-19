package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CompanyMemberFindIDResponseDTO {
    private String memberID;
    private LocalDateTime memberJoinDate;
}
