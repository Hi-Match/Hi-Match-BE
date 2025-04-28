package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationSchool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationSchoolRepository extends JpaRepository<ApplicationSchool, Long>, ApplicationSchoolRepositoryCustom {
}
