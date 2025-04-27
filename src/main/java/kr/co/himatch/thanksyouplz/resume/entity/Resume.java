package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.*;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESUME")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeNo;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member memberNo;

    @Column(name = "RESUME_TITLE", length = 255, nullable = false)
    private String resumeTitle;

    @Column(name = "RESUME_NAME", length = 50, nullable = false)
    private String resumeName;

    @Column(name = "RESUME_ENGNAME", length = 30)
    private String resumeEngname;

    @Column(name = "RESUME_MAIL", length = 255)
    private String resumeMail;

    @Column(name = "RESUME_TEL", length = 30)
    private String resumeTel;

    @Column(name = "RESUME_ADDRESS", length = 200)
    private String resumeAddress;

    @Column(name = "RESUME_BIRTHDAY", length = 200)
    private String resumeBirthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESUME_GENDER")
    private Gender resumeGender;

    @Column(name = "RESUME_IMG", length = 2000)
    private String resumeImg;

    @Column(name = "RESUME_DATE")
    private LocalDateTime resumeDate;

    @Column(name = "RESUME_UPDATE")
    private LocalDateTime resumeUpdate;

    @Column(name = "RESUME_PORTFOLIO", length = 2000)
    private String resumePortfolio;

    @Column(name = "RESUME_AMBITION", length = 255)
    private String resumeAmbition;

    @Convert(converter = ArmyTypeConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "RESUME_ARMY_TYPE")
    private ArmyType resumeArmyType;

    @Column(name = "RESUME_ARMY_DATE")
    private LocalDateTime resumeArmyDate;

    @Column(name = "RESUME_ARMY_END")
    private LocalDateTime resumeArmyEnd;

    @Convert(converter = ArmyPartConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "RESUME_ARMY_PART")
    private ArmyPart resumeArmyPart;

    @Column(name = "RESUME_DISABILITY_TYPE", length = 30)
    private String resumeDisabilityType;

    @Column(name = "RESUME_DISABILITY", length = 100)
    private String resumeDisability;

    @Column(name = "RESUME_REWARDING_PATRIOTISM", length = 20)
    private String resumeRewardingPatriotism;

}
