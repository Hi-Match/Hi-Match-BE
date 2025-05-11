package kr.co.himatch.thanksyouplz.company.repository;

import kr.co.himatch.thanksyouplz.company.entity.Company;
import kr.co.himatch.thanksyouplz.company.entity.CompanyTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyTagRepository extends JpaRepository<CompanyTag, Long>, CompanyTagRepositoryCustom {
    List<CompanyTag> findByCompanyNo(Company companyNo);
}
