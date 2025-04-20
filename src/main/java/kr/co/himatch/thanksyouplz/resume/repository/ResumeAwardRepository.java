package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.entity.Resume;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeAward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeAwardRepository extends JpaRepository<ResumeAward, Long>, ResumeAwardRepositoryCustom {
}
