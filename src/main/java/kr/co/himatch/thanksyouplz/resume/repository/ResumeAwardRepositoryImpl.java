package kr.co.himatch.thanksyouplz.resume.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.resume.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.resume.entity.QResumeAward.resumeAward;

public class ResumeAwardRepositoryImpl implements ResumeAwardRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ResumeAwardDTO> selectResumeAward(Long resumeNo) {
        return queryFactory.select(
                        Projections.constructor(ResumeAwardDTO.class,
                                resumeAward.awaTitle,
                                resumeAward.awaCompetitionName,
                                resumeAward.awaOrgan,
                                resumeAward.awaDate,
                                resumeAward.awaContent
                        )
                ).from(resumeAward)
                .where(resumeAward.resumeNo.resumeNo.eq(resumeNo))
                .fetch();
    }

}
