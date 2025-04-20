package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESUME_EXPERIENCE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ResumeExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expNo;

    @ManyToOne
    @JoinColumn(name = "RESUME_NO")
    private Resume resumeNo;

    @Column(name = "EXP_COMPANY_NAME", length = 100, nullable = false)
    private String expCompanyName;

    @Column(name = "EXP_POSITION", length = 100, nullable = false)
    private String expPosition;

    @Column(name = "EXP_START_DATE", nullable = false)
    private LocalDateTime expStartDate;

    @Column(name = "EXP_END_DATE")
    private LocalDateTime expEndDate;

    @Column(name = "EXP_PART",columnDefinition = "LONGTEXT")
    private String expPart;

    @Column(name = "EXP_ACHIEVEMENT",columnDefinition = "LONGTEXT")
    private String expAchievement;

    @Column(name = "EXP_IS_CURRENT")
    private Boolean expIsCurrent;

    @Column(name = "EXP_CREATE")
    private LocalDateTime expCreate;

    @Column(name = "EXP_UPDATE")
    private LocalDateTime expUpdate;
}