package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyApplyDetailSchoolResponseDTO;
import kr.co.himatch.thanksyouplz.resume.entity.SchPart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QApplicationSchool.applicationSchool;

public class ApplicationSchoolRepositoryImpl implements ApplicationSchoolRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    // 지원서 학력 조회
    @Override
    public List<ApplicationCompanyApplyDetailSchoolResponseDTO> selectSchool(Long applicationNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyApplyDetailSchoolResponseDTO.class, applicationSchool.aSchName, applicationSchool.aSchMajor, applicationSchool.aSchMinor, applicationSchool.aSchMultiple, applicationSchool.aSchDegree, applicationSchool.aSchGraduationDate, applicationSchool.aSchAdmissionDate, applicationSchool.aSchGpa, applicationSchool.aSchStandardGpa, applicationSchool.aSchPart, applicationSchool.aSchLev)
                )
                .from(applicationSchool)
                .where(applicationSchool.applicationNo.applicationNo.eq(applicationNo))
                .fetch();
    }

    // 지원서 최종학력 조회
    @Override
    public SchPart selectLastEducationPartByApplicationNo(Long applicationNo) {
        return queryFactory.select(applicationSchool.aSchPart)
                .from(applicationSchool)
                .where(applicationSchool.applicationNo.applicationNo.eq(applicationNo))
                .orderBy(applicationSchool.aSchLev.desc())
                .fetchFirst();
    }
}
