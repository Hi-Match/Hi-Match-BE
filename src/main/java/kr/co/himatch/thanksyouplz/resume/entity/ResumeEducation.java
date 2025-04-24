package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESUME_EDUCATION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ResumeEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eduNo;

    @ManyToOne
    @JoinColumn(name = "RESUME_NO")
    private Resume resumeNo;

    @Column(name = "EDU_TITLE", length = 255, nullable = false)
    private String eduTitle;

    @Column(name = "EDU_ORGAN", length = 255)
    private String eduOrgan;

    @Column(name = "EDU_CONTENT", length = 255)
    private String eduContent;

    @Column(name = "EDU_TIME")
    private Integer eduTime;

    @Column(name = "EDU_START_DATE")
    private LocalDateTime eduStartDate;

    @Column(name = "EDU_END_DATE")
    private LocalDateTime eduEndDate;

}