package kr.co.himatch.thanksyouplz.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class CompanySignupResponseDTO {
    private String memberID;
    private String companyName;
    private String memberName;
    private LocalDateTime memberJoinDate;
}
