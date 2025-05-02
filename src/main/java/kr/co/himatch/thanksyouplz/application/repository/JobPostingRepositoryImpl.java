package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyPostingResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QJobPosting.jobPosting;

public class JobPostingRepositoryImpl implements JobPostingRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ApplicationCompanyPostingResponseDTO> selectPostingList(Long memberNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyPostingResponseDTO.class, jobPosting.postingNo, jobPosting.postingTitle, jobPosting.postingPart)
                )
                .from(jobPosting)
                .where(jobPosting.companyNo.companyNo.eq(memberNo))
                .fetch();
    }
}
