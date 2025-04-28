package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationAward;
import kr.co.himatch.thanksyouplz.application.repository.ApplicationAwardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationAwardRepository extends JpaRepository<ApplicationAward, Long>, ApplicationAwardRepositoryCustom {
}
