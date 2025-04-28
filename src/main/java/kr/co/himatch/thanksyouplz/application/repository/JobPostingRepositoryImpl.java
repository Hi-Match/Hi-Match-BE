package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class JobPostingRepositoryImpl implements JobPostingRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;
}
