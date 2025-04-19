package kr.co.himatch.thanksyouplz.company.entity;
import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMPANY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyNo;

    @Column(name = "COMPANY_ID", nullable = false, length = 100, unique = true)
    private String companyID;

    @Column(name = "COMPANY_PW", nullable = false, length = 255)
    private String companyPass;

    @Column(name = "COMPANY_NAME", nullable = false, length = 100)
    private String companyName;

    @Column(name = "COMPANY_MANAGER_NAME", length = 100)
    private String companyManagerName;

    @Column(name = "COMPANY_ADDRESS", length = 255)
    private String companyAddress;

    @Column(name = "COMPANY_LICENSE", length = 100)
    private String companyLicense;

    @Column(name = "COMPANY_PHONE", length = 20)
    private String companyPhone;

    @Column(name = "COMPANY_MAIL", nullable = false, length = 30)
    private String companyMail;

    @Column(name = "COMPANY_INDUSTRY", length = 30)
    private String companyIndustry;

    @Column(name = "COMPANY_EMPLOYEE")
    private Integer companyEmployee;

    @Lob
    @Column(name = "COMPANY_DESCRIPTION")
    private String companyDescription;

    @Column(name = "COMPANY_CODE", length = 30)
    private String companyCode;

    @Column(name = "COMPANY_LOGO", length = 255)
    private String companyLogo;

    @Column(name = "COMPANY_CREATE", nullable = false)
    private LocalDateTime companyCreate;

    @Column(name = "COMPANY_UPDATE")
    private LocalDateTime companyUpdate;

    @Column(name = "COMPANY_REFRESH_TOKEN", length = 2000)
    private String companyRefreshToken;

    // Token 재발급
    public void changeToken(String companyRefreshToken){
        this.companyRefreshToken = companyRefreshToken;
    }

    // 비밀번호 찾기 시 > 임시 비밀번호로 변경
    public void temporaryChangePass(String companyPass){
        this.companyPass = BCrypt.hashpw(companyPass, BCrypt.gensalt());
    }
}