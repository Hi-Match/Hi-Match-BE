package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.Application;
import kr.co.himatch.thanksyouplz.application.repository.ApplicationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long>, ApplicationRepositoryCustom {
}
