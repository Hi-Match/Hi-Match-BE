package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailAwardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QApplicationAward.applicationAward;

public class ApplicationAwardRepositoryImpl implements ApplicationAwardRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ApplicationCompanyApplyDetailAwardResponseDTO> selectAward(Long applicationNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyApplyDetailAwardResponseDTO.class, applicationAward.aAwaTitle, applicationAward.aAwaCompetitionName, applicationAward.aAwaOrgan, applicationAward.aAwaDate, applicationAward.aAwaContent)
                )
                .from(applicationAward)
                .where(applicationAward.applicationNo.applicationNo.eq(applicationNo))
                .fetch();
    }
}
