package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION_EDUCATION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ApplicationEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aEduNo;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_NO")
    private Application applicationNo;

    @Column(name = "A_EDU_TITLE", length = 255, nullable = false)
    private String aEduTitle;

    @Column(name = "A_EDU_ORGAN", length = 255)
    private String aEduOrgan;

    @Column(name = "A_EDU_CONTENT", length = 255)
    private String aEuContent;

    @Column(name = "A_EDU_TIME")
    private Integer aEduTime;

    @Column(name = "A_EDU_START_DATE")
    private LocalDateTime aEduStartDate;

    @Column(name = "A_EDU_END_DATE")
    private LocalDateTime aEduEndDate;

}