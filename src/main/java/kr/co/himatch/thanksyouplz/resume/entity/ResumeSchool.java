package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESUME_SCHOOL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ResumeSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schNo;

    @ManyToOne
    @JoinColumn(name = "RESUME_NO")
    private Resume resumeNo;

    @Column(name = "SCH_NAME", length = 100, nullable = false)
    private String schName;

    @Column(name = "SCH_MAJOR", length = 100)
    private String schMajor;

    @Column(name = "SCH_MINOR", length = 100)
    private String schMinor;

    @Column(name = "SCH_MULTIPLE", length = 100)
    private String schMultiple;

    @Convert(converter = SchTypeConverter.class)
    @Column(name = "SCH_DEGREE")
    private SchType schDegree;

    @Column(name = "SCH_GRADUATION_DATE")
    private LocalDateTime schGraduationDate;

    @Column(name = "SCH_ADMISSION_DATE")
    private LocalDateTime schAdmissionDate;

    @Column(name = "SCH_GPA", precision = 3, scale = 2)
    private BigDecimal schGpa;

    @Column(name = "SCH_STANDARD_GPA", precision = 3, scale = 2)
    private BigDecimal schStandardGpa;

    @Column(name = "SCH_DESCRIPTION", columnDefinition = "LONGTEXT")
    private String schDescription;

    @Convert(converter = SchPartConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "SCH_PART")
    private SchPart schPart;

    @Column(name = "SCH_LEV")
    private Long schLev;
}