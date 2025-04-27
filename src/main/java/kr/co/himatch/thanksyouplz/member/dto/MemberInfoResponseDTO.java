package kr.co.himatch.thanksyouplz.member.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class MemberInfoResponseDTO {
    private String memberID;
    private String memberMail;
    private String memberName;
    private String memberPhone;
    private LocalDateTime memberJoinDate;
    private List<String> companyAddress;
    private List<String> companyPart;
    private List<String> companyType;
}
