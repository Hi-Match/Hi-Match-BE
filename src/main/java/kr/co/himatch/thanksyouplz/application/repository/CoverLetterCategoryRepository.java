package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.CoverLetterCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverLetterCategoryRepository extends JpaRepository<CoverLetterCategory, Long>, CoverLetterCategoryRepositoryCustom {
}
