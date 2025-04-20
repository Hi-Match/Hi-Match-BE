package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESUME_AWARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ResumeAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long awaNo;

    @ManyToOne
    @JoinColumn(name = "RESUME_NO")
    private Resume resumeNo;

    @Column(name = "AWA_TITLE", length = 50, nullable = false)
    private String awaTitle;

    @Column(name = "AWA_COMPETITION_NAME", length = 50)
    private String awaCompetitionName;

    @Column(name = "AWA_ORGAN", length = 50)
    private String awaOrgan;

    @Column(name = "AWA_DATE")
    private LocalDateTime awaDate;

    @Column(name = "AWA_CONTENT", length = 255)
    private String awaContent;
}