package kr.co.himatch.thanksyouplz.resume.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.resume.dto.*;
import kr.co.himatch.thanksyouplz.resume.entity.ResumeEducation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.resume.entity.QResume.resume;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeAward.resumeAward;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeCertificate.resumeCertificate;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeEducation.resumeEducation;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeExperience.resumeExperience;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeSchool.resumeSchool;

public class ResumeEducationRepositoryImpl implements ResumeEducationRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;


    // 이력서 교욱 조회
    @Override
    public List<ResumeEducationDTO> selectResumeEducation(Long resumeNo) {
        return queryFactory.select(
                        Projections.constructor(ResumeEducationDTO.class,
                                resumeEducation.eduTitle,
                                resumeEducation.eduOrgan,
                                resumeEducation.eduContent,
                                resumeEducation.eduTime,
                                resumeEducation.eduStartDate,
                                resumeEducation.eduEndDate
                        )
                ).from(resumeEducation)
                .where(resumeEducation.resumeNo.resumeNo.eq(resumeNo))
                .fetch();
    }

    // 이력서 교욱 삭제
    @Override
    public void deleteResumeEducation(Long resumeNo) {
        queryFactory.delete(resumeEducation)
                .where(resumeEducation.resumeNo.resumeNo.eq(resumeNo))
                .execute();
    }

}
