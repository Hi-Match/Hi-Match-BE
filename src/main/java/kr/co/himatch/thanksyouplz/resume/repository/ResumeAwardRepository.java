package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.entity.Resume;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeAward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeAwardRepository extends JpaRepository<ResumeAward, Long>, ResumeAwardRepositoryCustom {
    List<ResumeAward> findByResumeNo(Resume resume);
}
