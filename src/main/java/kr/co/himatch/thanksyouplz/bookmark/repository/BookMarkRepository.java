package kr.co.himatch.thanksyouplz.bookmark.repository;

import kr.co.himatch.thanksyouplz.application.entity.JobPosting;
import kr.co.himatch.thanksyouplz.bookmark.entity.BookMark;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long>, BookMarkRepositoryCustom {
    List<BookMark> findByMemberNoAndPostingNo(Member member, JobPosting posting);
}
