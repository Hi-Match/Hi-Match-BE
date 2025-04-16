package kr.co.himatch.thanksyouplz.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;
}
