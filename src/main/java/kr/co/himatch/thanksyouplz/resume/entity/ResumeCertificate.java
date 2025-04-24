package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESUME_CERTIFICATE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ResumeCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cerNo;

    @ManyToOne
    @JoinColumn(name = "RESUME_NO")
    private Resume resumeNo;

    @Column(name = "CER_TITLE", length = 100, nullable = false)
    private String cerTitle;

    @Column(name = "CER_ISSUING_AUTHORITY", length = 100)
    private String cerIssuingAuthority;

    @Column(name = "CER_DATE")
    private LocalDateTime cerDate;

    @Column(name = "CER_EXPIRE")
    private LocalDateTime cerExpire;

}
