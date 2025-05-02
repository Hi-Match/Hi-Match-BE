package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.entity.Resume;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeEducationRepository extends JpaRepository<ResumeEducation, Long>, ResumeEducationRepositoryCustom {
    List<ResumeEducation> findByResumeNo(Resume resume);
}
