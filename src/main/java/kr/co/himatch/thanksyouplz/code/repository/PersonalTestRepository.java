package kr.co.himatch.thanksyouplz.code.repository;

import kr.co.himatch.thanksyouplz.code.entity.PersonalTest;
import kr.co.himatch.thanksyouplz.code.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalTestRepository extends JpaRepository<PersonalTest, Long>, PersonalTestRepositoryCustom {

    // JPA 방식으로 조회 기능 추가
    List<PersonalTest> findByPerType(QuestionType perType);
}
