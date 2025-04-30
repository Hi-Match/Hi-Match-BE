package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberStatusResponseDTO;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QApplication.application;

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

    // 지원 상태에 따른 지원서 구하기
    @Override
    public List<ApplicationMemberStatusResponseDTO> selectPageByStatus(ApplicationStatus applicationStatus, Long memberNo, Long page) {
        if (applicationStatus.equals(ApplicationStatus.TOTAL)) {
            return queryFactory.select(
                            Projections.constructor(ApplicationMemberStatusResponseDTO.class, application.applicationNo, application.applicationName, application.postingNo.postingPart, application.applicationTel, application.applicationDate, application.applicationStatus)
                    )
                    .from(application)
                    .where(application.memberNo.memberNo.eq(memberNo))
                    .offset(10 * page)
                    .limit(10L)
                    .fetch();
        } else {
            return queryFactory.select(
                            Projections.constructor(ApplicationMemberStatusResponseDTO.class, application.applicationNo, application.applicationName, application.postingNo.postingPart, application.applicationTel, application.applicationDate, application.applicationStatus)
                    )
                    .from(application)
                    .where(application.applicationStatus.eq(applicationStatus).and(application.memberNo.memberNo.eq(memberNo)))
                    .offset(10 * page)
                    .limit(10L)
                    .fetch();
        }
    }

}
