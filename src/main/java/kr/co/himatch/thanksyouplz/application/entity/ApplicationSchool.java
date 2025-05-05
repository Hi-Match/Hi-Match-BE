package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import kr.co.himatch.thanksyouplz.application.entity.*;
import kr.co.himatch.thanksyouplz.resume.entity.SchPart;
import kr.co.himatch.thanksyouplz.resume.entity.SchPartConverter;
import kr.co.himatch.thanksyouplz.resume.entity.SchType;
import kr.co.himatch.thanksyouplz.resume.entity.SchTypeConverter;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION_SCHOOL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ApplicationSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aSchNo;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_NO")
    private Application applicationNo;

    @Column(name = "A_SCH_NAME", length = 100, nullable = false)
    private String aSchName;

    @Column(name = "A_SCH_MAJOR", length = 100)
    private String aSchMajor;

    @Column(name = "A_SCH_MINOR", length = 100)
    private String aSchMinor;

    @Column(name = "A_SCH_MULTIPLE", length = 100)
    private String aSchMultiple;

    @Convert(converter = SchTypeConverter.class)
    @Column(name = "A_SCH_DEGREE")
    private SchType aSchDegree;

    @Column(name = "A_SCH_GRADUATION_DATE")
    private LocalDateTime aSchGraduationDate;

    @Column(name = "A_SCH_ADMISSION_DATE")
    private LocalDateTime aSchAdmissionDate;

    @Column(name = "A_SCH_GPA", precision = 3, scale = 2)
    private BigDecimal aSchGpa;

    @Column(name = "A_SCH_STANDARD_GPA", precision = 3, scale = 2)
    private BigDecimal aSchStandardGpa;

    @Convert(converter = SchPartConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "A_SCH_PART")
    private SchPart aSchPart;

    @Column(name = "A_SCH_LEV")
    private Long aSchLev;
}