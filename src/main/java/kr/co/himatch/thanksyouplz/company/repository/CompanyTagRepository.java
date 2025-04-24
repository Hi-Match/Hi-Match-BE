package kr.co.himatch.thanksyouplz.company.repository;

import kr.co.himatch.thanksyouplz.company.entity.CompanyTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyTagRepository extends JpaRepository<CompanyTag, Long>, CompanyTagRepositoryCustom {
}
