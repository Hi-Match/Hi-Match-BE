package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION_CERTIFICATE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ApplicationCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aCerNo;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_NO")
    private Application applicationNo;

    @Column(name = "A_CER_TITLE", length = 100, nullable = false)
    private String aCerTitle;

    @Column(name = "A_CER_ISSUING_AUTHORITY", length = 100)
    private String aCerIssuingAuthority;

    @Column(name = "A_CER_DATE")
    private LocalDateTime aCerDate;

    @Column(name = "A_CER_EXPIRE")
    private LocalDateTime aCerExpire;

}
