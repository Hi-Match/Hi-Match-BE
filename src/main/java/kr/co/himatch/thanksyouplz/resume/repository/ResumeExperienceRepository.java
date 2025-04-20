package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.entity.Resume;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeExperienceRepository extends JpaRepository<ResumeExperience, Long>, ResumeExperienceRepositoryCustom {
}
