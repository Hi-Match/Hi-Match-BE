package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationEducation;
import kr.co.himatch.thanksyouplz.application.repository.ApplicationEducationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationEducationRepository extends JpaRepository<ApplicationEducation, Long>, ApplicationEducationRepositoryCustom {
}
