package kr.co.himatch.thanksyouplz.company.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.company.dto.CompanyInfoTagResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.company.entity.QCompanyTag.*;

public class CompanyTagRepositoryImpl implements CompanyTagRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<CompanyInfoTagResponseDTO> selectCompanyTags(Long memberNo) {
        return queryFactory.select(
                        Projections.constructor(CompanyInfoTagResponseDTO.class, companyTag.cTagName)
                ).from(companyTag)
                .where(companyTag.companyNo.companyNo.eq(memberNo))
                .fetch();
    }

    @Override
    public void deleteCompanyTags(Long memberNo) {
        queryFactory.delete(companyTag)
                .where(companyTag.companyNo.companyNo.eq(memberNo))
                .execute();
    }
}
