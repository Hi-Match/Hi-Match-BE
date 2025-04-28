package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CoverLetterRepositoryImpl implements CoverLetterRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;
}
