package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION_AWARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class ApplicationAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aAwaNo;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_NO")
    private Application applicationNo;

    @Column(name = "A_AWA_TITLE", length = 50, nullable = false)
    private String aAwaTitle;

    @Column(name = "A_AWA_COMPETITION_NAME", length = 50)
    private String aAwaCompetitionName;

    @Column(name = "A_AWA_ORGAN", length = 50)
    private String aAwaOrgan;

    @Column(name = "A_AWA_DATE")
    private LocalDateTime aAwaDate;

    @Column(name = "A_AWA_CONTENT", length = 255)
    private String aAwaContent;
}