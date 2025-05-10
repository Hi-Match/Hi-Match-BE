package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyPostingResponseDTO;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberJobListResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QJobPosting.jobPosting;

public class JobPostingRepositoryImpl implements JobPostingRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    // 채용 공고 목록 조회
    @Override
    public List<ApplicationCompanyPostingResponseDTO> selectPostingList(Long memberNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyPostingResponseDTO.class, jobPosting.postingNo, jobPosting.postingTitle, jobPosting.postingPart)
                )
                .from(jobPosting)
                .where(jobPosting.companyNo.companyNo.eq(memberNo))
                .fetch();
    }

    // 검색 필터링에 따른 채용 공고 목록 조회
    @Override
    public List<ApplicationMemberJobListResponseDTO> selectPostingBySearch(List<String> address, List<String> part, List<String> type, List<String> education, String keyword, Long page) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(buildAddressPredicate(address));
        builder.and(buildPartPredicate(part));
        builder.and(buildTypePredicate(type));
        builder.and(buildEducationPredicate(education));

        if (keyword != null && !keyword.isEmpty()) {
            builder.and(jobPosting.postingTitle.like("%" + keyword + "%")
                    .or(jobPosting.companyNo.companyName.like("%" + keyword + "%")));
        }

        return queryFactory.select(
                        Projections.constructor(ApplicationMemberJobListResponseDTO.class,
                                jobPosting.postingNo,
                                jobPosting.companyNo.companyName,
                                jobPosting.postingTitle,
                                jobPosting.companyNo.companyAddress,
                                jobPosting.postingType,
                                jobPosting.postingEducation,
                                jobPosting.postingDeadline
                        )
                )
                .from(jobPosting)
                .where(builder)
                .offset(10L * page)
                .limit(10L)
                .fetch();
    }

    // 검색 필터링에 따른 채용 공고 목록 페이지 수 조회
    @Override
    public Long selectPostingCountBySearch(List<String> address, List<String> part, List<String> type, List<String> education, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(buildAddressPredicate(address));
        builder.and(buildPartPredicate(part));
        builder.and(buildTypePredicate(type));
        builder.and(buildEducationPredicate(education));

        if (keyword != null && !keyword.isEmpty()) {
            builder.and(jobPosting.postingTitle.like("%" + keyword + "%")
                    .or(jobPosting.companyNo.companyName.like("%" + keyword + "%")));
        }

        return queryFactory.select(jobPosting.count())
                .from(jobPosting)
                .where(builder)
                .fetchFirst();
    }

    // 동적 쿼리를 위한 필터링 기능 추가 ( 주소 포함 여부로 조회)
    private Predicate buildAddressPredicate(List<String> address) {
        if (address == null || address.isEmpty()) {
            return null;
        }
        BooleanBuilder addressCondition = new BooleanBuilder();
        for (String addr : address) {
            addressCondition.or(jobPosting.companyNo.companyAddress.like("%" + addr + "%"));
        }
        return addressCondition;
    }

    // 동적 쿼리를 위한 필터링 기능 추가 ( 채용 직무 포함 여부로 조회)
    private Predicate buildPartPredicate(List<String> part) {
        if (part == null || part.isEmpty()) {
            return null;
        }
        BooleanBuilder partCondition = new BooleanBuilder();

        for (String p : part) {
            partCondition.or(jobPosting.postingPart.like("%" + p + "%"));
        }

        return partCondition;
    }

    // 동적 쿼리를 위한 필터링 기능 추가 ( 채용 타입 포함 여부로 조회)
    private Predicate buildTypePredicate(List<String> type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        BooleanBuilder typeCondition = new BooleanBuilder();

        for (String t : type) {
            typeCondition.or(jobPosting.postingType.like("%" + t + "%"));
        }
        return typeCondition;
    }

    // 동적 쿼리를 위한 필터링 기능 추가 ( 학력 타입 포함 여부로 조회)
    private Predicate buildEducationPredicate(List<String> education) {
        if (education == null || education.isEmpty()) {
            return null;
        }
        BooleanBuilder educationCondition = new BooleanBuilder();

        for (String e : education) {
            educationCondition.or(jobPosting.postingEducation.like("%" + e + "%"));
        }
        return educationCondition;

    }
}
