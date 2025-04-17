package kr.co.himatch.thanksyouplz.member.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class MemberFindIDResponseDTO {
    private String memberID;
    private LocalDateTime memberJoinDate;
}
