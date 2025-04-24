package kr.co.himatch.thanksyouplz.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagNo;

    @Column(name = "TAG_NAME", nullable = false, length = 30)
    private String tagName;
}
