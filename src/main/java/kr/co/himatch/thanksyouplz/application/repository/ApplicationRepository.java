package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.Application;
import kr.co.himatch.thanksyouplz.application.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long>, ApplicationRepositoryCustom {
    List<Application> findByPostingNo(JobPosting posting);
}
