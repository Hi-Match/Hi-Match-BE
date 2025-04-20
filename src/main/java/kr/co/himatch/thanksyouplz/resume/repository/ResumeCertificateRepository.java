package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.entity.Resume;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeCertificateRepository extends JpaRepository<ResumeCertificate, Long>, ResumeCertificateRepositoryCustom {
}
