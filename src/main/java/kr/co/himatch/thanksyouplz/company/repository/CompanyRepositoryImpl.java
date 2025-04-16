package kr.co.himatch.thanksyouplz.company.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;
}
