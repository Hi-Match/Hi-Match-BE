package kr.co.himatch.thanksyouplz.code.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PERSONAL_TEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class PersonalTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perNo;

    @Column(name = "PER_QUESTION", length = 255, nullable = false)
    private String perQuestion;

    @Enumerated(EnumType.STRING)
    @Column(name = "PER_TYPE", length = 255, nullable = false)
    private QuestionType perType;
}
