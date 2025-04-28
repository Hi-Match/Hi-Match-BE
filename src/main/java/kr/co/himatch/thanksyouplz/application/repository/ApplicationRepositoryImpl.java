package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;

import static kr.co.himatch.thanksyouplz.application.entity.QApplication.*;

public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    // 지원 상태에 따른 페이지 수 구하기
    @Override
    public Long selectMaxPageByApplicationStatus(ApplicationStatus applicationStatus, Long memberNo) {
        return queryFactory.select(application.count())
                .from(application)
                .where(application.applicationStatus.eq(applicationStatus).and(application.memberNo.memberNo.eq(memberNo)))
                .fetchFirst();
    }

    // 지원 상태에 따른 페이지 수 구하기
    @Override
    public Long selectCountByApplicationStatus(ApplicationStatus applicationStatus, Long memberNo) {
        return queryFactory.select(application.count())
                .from(application)
                .where(application.applicationStatus.eq(applicationStatus).and(application.memberNo.memberNo.eq(memberNo)))
                .fetchFirst();
    }

    // 지원 모든 상태에 따른 페이지 수 구하기
    @Override
    public Long selectCountByApplicationAllStatus(Long memberNo) {
        return queryFactory.select(application.count())
                .from(application)
                .where(application.memberNo.memberNo.eq(memberNo))
                .fetchFirst();
    }
}
