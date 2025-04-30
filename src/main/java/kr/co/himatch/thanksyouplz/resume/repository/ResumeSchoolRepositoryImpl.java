package kr.co.himatch.thanksyouplz.resume.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.resume.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.resume.entity.QResumeSchool.resumeSchool;

public class ResumeSchoolRepositoryImpl implements ResumeSchoolRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ResumeSchoolDTO> selectResumeSchool(Long resumeNo) {
        return queryFactory.select(Projections.constructor(ResumeSchoolDTO.class, resumeSchool.schName, resumeSchool.schMajor,resumeSchool.schMinor,resumeSchool.schMultiple, resumeSchool.schDegree, resumeSchool.schGraduationDate, resumeSchool.schAdmissionDate, resumeSchool.schGpa, resumeSchool.schStandardGpa, resumeSchool.schPart, resumeSchool.schLev))
                .from(resumeSchool)
                .where(resumeSchool.resumeNo.resumeNo.eq(resumeNo))
                .fetch();
    }

    @Override
    public void deleteResumeSchool(Long resumeNo) {
        queryFactory.delete(resumeSchool)
                .where(resumeSchool.resumeNo.resumeNo.eq(resumeNo))
                .execute();

    }
}
