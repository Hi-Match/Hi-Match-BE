package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.CompanyQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyQuestionsRepository extends JpaRepository<CompanyQuestions, Long>, CompanyQuestionsRepositoryCustom{
}
