package kr.co.himatch.thanksyouplz.company.repository;

import kr.co.himatch.thanksyouplz.company.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag, Long>, TagRepositoryCustom {
}
