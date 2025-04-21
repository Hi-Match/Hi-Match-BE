package kr.co.himatch.thanksyouplz.resume.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.resume.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.resume.entity.QResume.resume;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeAward.resumeAward;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeCertificate.resumeCertificate;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeEducation.resumeEducation;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeExperience.resumeExperience;
import static kr.co.himatch.thanksyouplz.resume.entity.QResumeSchool.resumeSchool;

public class ResumeExperienceRepositoryImpl implements ResumeExperienceRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ResumeExperienceDTO> selectResumeExperience(Long resumeNo) {
        return queryFactory.select(
                        Projections.constructor(ResumeExperienceDTO.class,
                                resumeExperience.expCompanyName,
                                resumeExperience.expPosition,
                                resumeExperience.expStartDate,
                                resumeExperience.expEndDate,
                                resumeExperience.expPart,
                                resumeExperience.expAchievement,
                                resumeExperience.expIsCurrent
                        )
                ).from(resumeExperience)
                .where(resumeEducation.resumeNo.resumeNo.eq(resumeNo))
                .fetch();
    }

    @Override
    public void deleteResumeExperience(Long resumeNo) {
        queryFactory.delete(resumeExperience)
                .where(resumeEducation.resumeNo.resumeNo.eq(resumeNo))
                .execute();
    }

}
