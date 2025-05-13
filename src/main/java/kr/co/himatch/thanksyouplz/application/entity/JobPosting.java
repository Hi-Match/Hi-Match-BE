package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @Column(name = "POSTING_SAL", length = 100)
    private String postingSal;

    @Column(name = "POSTING_EXPERIENCE", length = 50)
    private String postingExperience;

    @Column(name = "POSTING_EDUCATION", length = 50)
    private String postingEducation;

    @Column(name = "POSTING_LOCATION", length = 100)
    private String postingLocation;

    @Column(name = "POSTING_TYPE", length = 100)
    private String postingType;

    @Column(name = "POSTING_WORK_TYPE", length = 100)
    private String postingWorkType;

    @Column(name = "POSTING_WORK_START_TIME")
    private LocalTime postingWorkStartTime;

    @Column(name = "POSTING_WORK_END_TIME")
    private LocalTime postingWorkEndTime;

    @Column(name = "POSTING_IS_FINISH")
    private Boolean postingIsFinish;

    @Column(name = "POSTING_DEADLINE")
    private LocalDateTime postingDeadline;

    @Column(name = "POSTING_CREATE")
    private LocalDateTime postingCreate;

    @Column(name = "POSTING_UPDATE")
    private LocalDateTime postingUpdate;

    public void changePostingInfo(String postingTitle, String postingContent, String postingPart, String postingSal, String postingExperience, String postingEducation, String postingLocation, String postingType, String postingWorkType, LocalTime postingWorkStartTime, LocalTime postingWorkEndTime, Boolean postingIsFinish, LocalDateTime postingDeadLine) {
        this.postingTitle = postingTitle;
        this.postingContent = postingContent;
        this.postingPart = postingPart;
        this.postingSal = postingSal;
        this.postingExperience = postingExperience;
        this.postingEducation = postingEducation;
        this.postingLocation = postingLocation;
        this.postingType = postingType;
        this.postingWorkType = postingWorkType;
        this.postingWorkStartTime = postingWorkStartTime;
        this.postingWorkEndTime = postingWorkEndTime;
        this.postingIsFinish = postingIsFinish;
        this.postingDeadline = postingDeadLine;
        this.postingUpdate = LocalDateTime.now();
    }

}
