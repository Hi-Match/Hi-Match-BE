package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailEducationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QApplicationEducation.applicationEducation;

public class ApplicationEducationRepositoryImpl implements ApplicationEducationRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    // 지원서 교육 조회
    @Override
    public List<ApplicationCompanyApplyDetailEducationResponseDTO> selectEducation(Long applicationNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyApplyDetailEducationResponseDTO.class, applicationEducation.aEduTitle, applicationEducation.aEduOrgan, applicationEducation.aEuContent, applicationEducation.aEduTime, applicationEducation.aEduStartDate, applicationEducation.aEduEndDate)
                )
                .from(applicationEducation)
                .where(applicationEducation.applicationNo.applicationNo.eq(applicationNo))
                .fetch();
    }
}
