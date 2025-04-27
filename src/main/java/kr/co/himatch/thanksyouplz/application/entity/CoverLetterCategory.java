package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COVER_LETTER_CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class CoverLetterCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coverLetterNo;

    @Column(name = "COVER_CATEGORY_TITLE", length = 255, nullable = false)
    private String coverCategoryTitle;
}
