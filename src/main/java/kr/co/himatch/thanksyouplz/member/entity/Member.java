package kr.co.himatch.thanksyouplz.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(name = "member_id", length = 100, unique = true)
    private String memberID;

    @Column(name = "member_pass", length = 2000, nullable = false)
    private String memberPass;

    @Column(name = "member_name", length = 100, nullable = false)
    private String memberName;

    @Column(name = "member_mail", length = 100, nullable = false)
    private String memberMail;

    @Column(name = "member_phone", length = 100, nullable = false)
    private String memberPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_social_type")
    private SocialType socialType;

    @Column(name = "member_social_id", length = 200)
    private String memberSocialID;

    @Column(name = "member_refresh_token", length = 2000)
    private String memberRefreshToken;

    @Column(name = "member_nickname", length = 100, nullable = false)
    private String memberNickName;

    @Column(name = "member_random", length = 100, nullable = false)
    private String memberRandom;

    @Column(name = "member_file", length = 3000)
    private String memberFile;

    @Column(name = "member_join_date", nullable = false)
    private LocalDateTime memberJoinDate;

    @Column(name = "member_company_address", length = 200)
    private String memberCompanyAddress;

    @Column(name = "member_company_part", length = 200)
    private String memberCompanyPart;

    @Column(name = "member_company_contract", length = 20)
    private String memberCompanyContract;

    @Column(name = "member_code", length = 20)
    private String memberCode;

    @Column(name = "member_test_date")
    private LocalDateTime memberTestDate;

    // 프로필 편집 - 휴대폰 번호 변경
    public void changePhone(String memberPhone){
        this.memberPhone = memberPhone;
    }

    // 프로필 편집 - 메일 변경
    public void changeMail(String memberMail){
        this.memberMail = memberMail;
    }

    // 프로필 편집 - 비밀번호 변경
    public void changePass(String memberPass){
        this.memberPass = memberPass;
    }

    // Token 재발급
    public void changeToken(String memberRefreshToken){
        this.memberRefreshToken = memberRefreshToken;
    }

    // 비밀번호 찾기 시 > 임시 비밀번호로 변경
    public void temporaryChangePass(String memberPass){
        this.memberPass = BCrypt.hashpw(memberPass, BCrypt.gensalt());
    }


}
