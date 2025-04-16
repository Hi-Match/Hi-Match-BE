package kr.co.himatch.thanksyouplz.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@ToString
public class MemberSignUpResponseDTO {
    private String memberID;
    private String memberName;
    private String memberNickName;
    private String memberRandom;
    private LocalDateTime memberJoinDate;
}
