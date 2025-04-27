package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COMPANY_QUESTIONS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class CompanyQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionNo;

    @ManyToOne
    @JoinColumn(name = "POSTING_NO")
    private JobPosting postingNo;

    @Column(name = "QUESTION_TITLE", length = 255, nullable = false)
    private String questionTitle;

    @Column(name = "QUESTION_LENGTH", nullable = false)
    private Integer questionLength;

}
