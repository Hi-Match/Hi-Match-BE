package kr.co.himatch.thanksyouplz.company.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.company.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import static kr.co.himatch.thanksyouplz.company.entity.QCompany.*;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    // 회원 가입 시, ID 중복 검사
    @Override
    public Optional<String> selectMemberID(String memberID) {
        return Optional.ofNullable(
                queryFactory.select(company.companyID)
                        .from(company)
                        .where(company.companyID.eq(memberID))
                        .fetchFirst()
        );
    }

    // 기업용 일반 로그인
    @Override
    public Company selectID(String memberID) {
        return queryFactory.select(company)
                .from(company)
                .where(company.companyID.eq(memberID))
                .fetchFirst();
    }
}
