package kr.co.himatch.thanksyouplz.company.entity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "COMPANY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @Column(name = "COMPANY_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyNo;

    @Column(name = "COMPANY_ID", nullable = false, length = 100)
    private String companyId;

    @Column(name = "COMPANY_PW", nullable = false, length = 255)
    private String companyPw;

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

    @Column(name = "COMPANY_CREATE")
    private Timestamp companyCreate;

    @Column(name = "COMPANY_UPDATE")
    private Timestamp companyUpdate;

}