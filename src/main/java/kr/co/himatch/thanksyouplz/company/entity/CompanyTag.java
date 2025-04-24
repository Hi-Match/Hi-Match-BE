package kr.co.himatch.thanksyouplz.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COMPANY_TAG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class CompanyTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cTagNo;

    @ManyToOne
    @JoinColumn(name = "COMPANY_NO")
    private Company companyNo;

    @Column(name = "C_TAG_NAME", nullable = false, length = 30)
    private String cTagName;

}
