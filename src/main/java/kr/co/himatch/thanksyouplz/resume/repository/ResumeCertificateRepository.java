package kr.co.himatch.thanksyouplz.resume.repository;

import kr.co.himatch.thanksyouplz.resume.entity.Resume;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeCertificateRepository extends JpaRepository<ResumeCertificate, Long>, ResumeCertificateRepositoryCustom {
    List<ResumeCertificate> findByResumeNo(Resume resume);
}
