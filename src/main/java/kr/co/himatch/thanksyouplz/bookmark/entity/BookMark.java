package kr.co.himatch.thanksyouplz.bookmark.entity;

import jakarta.persistence.*;
import kr.co.himatch.thanksyouplz.application.entity.JobPosting;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "BOOKMARK")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookMarkNo;

    @Column(name = "BOOKMARK_DATE")
    private LocalDateTime bookMarkDate;

    @ManyToOne
    @JoinColumn(name = "POSTING_NO")
    private JobPosting postingNo;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member memberNo;



}
