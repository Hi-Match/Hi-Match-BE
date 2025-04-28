package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationExperience;
import kr.co.himatch.thanksyouplz.application.repository.ApplicationExperienceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationExperienceRepository extends JpaRepository<ApplicationExperience, Long>, ApplicationExperienceRepositoryCustom {
}
