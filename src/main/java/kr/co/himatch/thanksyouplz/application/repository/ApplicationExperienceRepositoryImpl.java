package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailExperienceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QApplicationExperience.applicationExperience;

public class ApplicationExperienceRepositoryImpl implements ApplicationExperienceRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    // 지원서 경력 조회
    @Override
    public List<ApplicationCompanyApplyDetailExperienceResponseDTO> selectExperience(Long applicationNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyApplyDetailExperienceResponseDTO.class, applicationExperience.aExpCompanyName, applicationExperience.aExpPosition, applicationExperience.aExpStartDate, applicationExperience.aExpEndDate, applicationExperience.aExpPart, applicationExperience.aExpAchievement, applicationExperience.aExpIsCurrent)
                )
                .from(applicationExperience)
                .where(applicationExperience.applicationNo.applicationNo.eq(applicationNo))
                .fetch();
    }
}
