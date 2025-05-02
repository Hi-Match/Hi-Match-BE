package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.resume.entity.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo;

    @ManyToOne
    @JoinColumn(name = "POSTING_NO")
    private JobPosting postingNo;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member memberNo;

    @Column(name = "APPLICATION_TITLE", length = 255, nullable = false)
    private String applicationTitle;

    @Column(name = "APPLICATION_NAME", length = 50, nullable = false)
    private String applicationName;

    @Column(name = "APPLICATION_ENGNAME", length = 30)
    private String applicationEngname;

    @Column(name = "APPLICATION_MAIL", length = 255)
    private String applicationMail;

    @Column(name = "APPLICATION_TEL", length = 30)
    private String applicationTel;

    @Column(name = "APPLICATION_ADDRESS", length = 200)
    private String applicationAddress;

    @Column(name = "APPLICATION_BIRTHDAY", length = 200)
    private String applicationBirthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "APPLICATION_GENDER")
    private Gender applicationGender;

    @Column(name = "APPLICATION_IMG", length = 2000)
    private String applicationImg;

    @Column(name = "APPLICATION_PORTFOLIO", length = 2000)
    private String applicationPortfolio;

    @Column(name = "APPLICATION_AMBITION", length = 255)
    private String applicationAmbition;

    @Convert(converter = ArmyTypeConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "APPLICATION_ARMY_TYPE")
    private ArmyType applicationArmyType;

    @Column(name = "APPLICATION_ARMY_DATE")
    private LocalDateTime applicationArmyDate;

    @Column(name = "APPLICATION_ARMY_END")
    private LocalDateTime applicationArmyEnd;

    @Convert(converter = ArmyPartConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "APPLICATION_ARMY_PART")
    private ArmyPart applicationArmyPart;

    @Column(name = "APPLICATION_DISABILITY_TYPE", length = 30)
    private String applicationDisabilityType;

    @Column(name = "APPLICATION_DISABILITY", length = 100)
    private String applicationDisability;

    @Column(name = "APPLICATION_REWARDING_PATRIOTISM", length = 20)
    private String applicationRewardingPatriotism;

    @Column(name = "APPLICATION_DATE")
    private LocalDateTime applicationDate;

    @Column(name = "APPLICATION_END_DATE")
    private LocalDateTime applicationEndDate;

    @Convert(converter = ApplicationStatusConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "APPLICATION_STATUS")
    private ApplicationStatus applicationStatus;

    @Column(name = "APPLICATION_ALARM")
    private Boolean applicationAlarm;

    @Column(name = "APPLICATION_GRADE")
    private Integer applicationGrade;

    @Column(name = "APPLICATION_RESUILT")
    private Boolean applicationResult;

    @Column(name = "APPLICATION_PF")
    private Boolean applicationPf;


    @Column(name = "APPLICATION_CREATE")
    private LocalDateTime applicationCreate;

    @Column(name = "APPLICATION_UPDATE")
    private LocalDateTime applicationUpdate;

    public void changeApplicationStatus(ApplicationStatus status) {
        this.applicationStatus = status;
    }

}
