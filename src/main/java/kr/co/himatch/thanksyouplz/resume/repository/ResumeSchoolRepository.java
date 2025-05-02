package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.entity.Resume;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeSchoolRepository extends JpaRepository<ResumeSchool, Long>, ResumeSchoolRepositoryCustom {
    List<ResumeSchool> findByResumeNo(Resume resume);
}
