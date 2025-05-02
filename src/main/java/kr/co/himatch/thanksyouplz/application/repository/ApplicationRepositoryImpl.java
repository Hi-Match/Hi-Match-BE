package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyListResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberStatusResponseDTO;
import kr.co.himatch.thanksyouplz.application.entity.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QApplication.application;

public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    // 지원 상태에 따른 페이지 수 구하기
    @Override
    public Long selectMaxPageByApplicationStatus(ApplicationStatus applicationStatus, Long memberNo) {
        if (ApplicationStatus.TOTAL.equals(applicationStatus)) {
            return queryFactory.select(application.count())
                    .from(application)
                    .where(application.memberNo.memberNo.eq(memberNo))
                    .fetchFirst();
        } else {
            return queryFactory.select(application.count())
                    .from(application)
                    .where(application.applicationStatus.eq(applicationStatus).and(application.memberNo.memberNo.eq(memberNo)))
                    .fetchFirst();
        }
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
        return queryFactory.select(
                        Projections.constructor(ApplicationMemberStatusResponseDTO.class, application.applicationNo, application.applicationTitle, application.postingNo.postingPart, application.postingNo.postingType, application.applicationDate, application.applicationStatus)
                )
                .from(application)
                .where(application.memberNo.memberNo.eq(memberNo)
                        , equalsApplicationStatus(applicationStatus))
                .offset(10 * page)
                .limit(10L)
                .fetch();
    }

    @Override
    public Long selectPageSearchCountByStatus(String keyword, ApplicationStatus applicationStatus, Long memberNo) {
        return queryFactory.select(application.count())
                .from(application)
                .where(application.memberNo.memberNo.eq(memberNo)
                        , likeApplicationTitle(keyword)
                        , equalsApplicationStatus(applicationStatus)
                )
                .fetchFirst();
    }

    @Override
    public List<ApplicationMemberStatusResponseDTO> selectPageSearchByStatus(String keyword, ApplicationStatus applicationStatus, Long page, Long memberNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationMemberStatusResponseDTO.class, application.applicationNo, application.applicationTitle, application.postingNo.postingPart, application.postingNo.postingType, application.applicationDate, application.applicationStatus)
                )
                .from(application)
                .where(application.memberNo.memberNo.eq(memberNo)
                        , likeApplicationTitle(keyword)
                        , equalsApplicationStatus(applicationStatus)
                )
                .offset(10L * page)
                .limit(10L)
                .fetch();
    }

    // 채용 공고의 지원자 목록 조회
    @Override
    public List<ApplicationCompanyListResponseDTO> selectCompanyListByPostingNo(Long posingNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyListResponseDTO.class, application.applicationNo, application.applicationDate, application.applicationStatus, application.applicationName, application.applicationGrade)
                )
                .from(application)
                .where(application.postingNo.postingNo.eq(posingNo))
                .fetch();
    }

    private BooleanExpression likeApplicationTitle(String keyword) {
        return StringUtils.hasText(keyword) ? application.applicationTitle.like("%" + keyword + "%") : null;
    }

    private BooleanExpression equalsApplicationStatus(ApplicationStatus status) {
        return ApplicationStatus.TOTAL.equals(status) ? null : application.applicationStatus.eq(status);
    }
}
