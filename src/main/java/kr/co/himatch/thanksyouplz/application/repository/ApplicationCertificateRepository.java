package kr.co.himatch.thanksyouplz.application.repository;

import kr.co.himatch.thanksyouplz.application.entity.ApplicationCertificate;
import kr.co.himatch.thanksyouplz.application.repository.ApplicationCertificateRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationCertificateRepository extends JpaRepository<ApplicationCertificate, Long>, ApplicationCertificateRepositoryCustom {
}
