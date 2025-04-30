package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COVER_LETTER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class CoverLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coverLetterNo;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_NO")
    private Application applicationNo;

    @Column(name = "COVER_QUESTION", length = 255, nullable = false)
    private String coverQuestion;

    @Lob
    @Column(name = "COVER_CONTENT")
    private String coverContent;

    @Column(name = "COVER_LENGTH")
    private Integer coverLength;

}
