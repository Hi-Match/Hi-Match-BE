package kr.co.himatch.thanksyouplz.bookmark.repository;

import kr.co.himatch.thanksyouplz.bookmark.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<BookMark,Long>, BookMarkRepositoryCustom {
}
