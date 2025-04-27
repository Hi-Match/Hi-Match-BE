package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "JOB_POSTING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postingNo;

    @ManyToOne
    @JoinColumn(name = "COMPANY_NO")
    private Company companyNo;

    @Column(name = "POSTING_TITLE", length = 255)
    private String postingTitle;

    @Lob
    @Column(name = "POSTING_CONTENT")
    private String postingContent;

    @Column(name = "POSTING_PART", length = 20)
    private String postingPart;

    @Column(name = "POSTING_SAL")
    private Integer postingSal;

    @Column(name = "POSTING_EXPERIENCE", length = 50)
    private String postingExperience;

    @Column(name = "POSTING_EDUCATION", length = 50)
    private String postingEducation;

    @Column(name = "POSTING_LOCATION", length = 100)
    private String postingLocation;

    @Column(name = "POSTING_IS_DEGREE")
    private Boolean postingIsDegree;

    @Column(name = "POSTING_IS_EXP")
    private Boolean postingIsExp;

    @Column(name = "POSTING_TYPE", length = 100)
    private String postingType;

    @Column(name = "POSTING_WORK_TYPE", length = 100)
    private String postingWorkType;

    @Column(name = "POSTING_WORK_START_TIME")
    private LocalDateTime postingWorkStartTime;

    @Column(name = "POSTING_WORK_END_TIME")
    private LocalDateTime postingWorkEndTime;

    @Column(name = "POSTING_COMPANY_URL", length = 255)
    private String postingCompanyUrl;

    @Column(name = "POSTING_ETC", length = 255)
    private String postingEtc;

    @Column(name = "POSTING_IS_FINISH")
    private Boolean postingIsFinish;

    @Column(name = "POSTING_DEADLINE")
    private LocalDateTime postingDeadline;

    @Column(name = "POSTING_CREATE")
    private LocalDateTime postingCreate;

    @Column(name = "POSTING_UPDATE")
    private LocalDateTime postingUpdate;

}
