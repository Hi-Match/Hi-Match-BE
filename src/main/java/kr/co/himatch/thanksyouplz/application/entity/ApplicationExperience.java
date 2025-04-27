package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION_EXPERIENCE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ApplicationExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aExpNo;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_NO")
    private Application applicationNo;

    @Column(name = "A_EXP_COMPANY_NAME", length = 100, nullable = false)
    private String aExpCompanyName;

    @Column(name = "A_EXP_POSITION", length = 100, nullable = false)
    private String aExpPosition;

    @Column(name = "A_EXP_START_DATE", nullable = false)
    private LocalDateTime aExpStartDate;

    @Column(name = "A_EXP_END_DATE")
    private LocalDateTime aExpEndDate;

    @Column(name = "A_EXP_PART",columnDefinition = "LONGTEXT")
    private String aExpPart;

    @Column(name = "A_EXP_ACHIEVEMENT",columnDefinition = "LONGTEXT")
    private String aExpAchievement;

    @Column(name = "A_EXP_IS_CURRENT")
    private Boolean aExpIsCurrent;

}