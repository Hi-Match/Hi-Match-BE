package kr.co.himatch.thanksyouplz.resume.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.resume.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.resume.entity.QResume.resume;

public class ResumeRepositoryImpl implements ResumeRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    // 이력서 목록 조회
    @Override
    public List<ResumeListResponseDTO> selectResumeList(Long memberNo) {
        return queryFactory.select(
                        Projections.constructor(ResumeListResponseDTO.class, resume.resumeNo, resume.resumeTitle, resume.resumeDate)
                ).from(resume)
                .where(resume.memberNo.memberNo.eq(memberNo))
                .fetch();
    }

    // 이력서 상세 조회
    @Override
    public ResumeDetailDTO selectResumeDetail(Long memberNo, Long resumeNo) {
        return queryFactory.select(
                        Projections.constructor(ResumeDetailDTO.class,
                                resume.resumeNo,
                                resume.resumeTitle,
                                resume.resumeName,
                                resume.resumeEngname,
                                resume.resumeMail,
                                resume.resumeTel,
                                resume.resumeAddress,
                                resume.resumeBirthday,
                                resume.resumeGender,
                                resume.resumeImg,
                                resume.resumeDate,
                                resume.resumePortfolio,
                                resume.resumeAmbition,
                                resume.resumeArmyType,
                                resume.resumeArmyDate,
                                resume.resumeArmyEnd,
                                resume.resumeArmyPart,
                                resume.resumeDisability,
                                resume.resumeDisabilityType,
                                resume.resumeRewardingPatriotism
                        )
                ).from(resume)
                .where(resume.resumeNo.eq(resumeNo)
                        .and(resume.memberNo.memberNo.eq(memberNo)))
                .fetchFirst();
    }

    // 이력서 삭제
    @Override
    public void deleteResumeDetail(Long memberNo, Long resumeNo) {
        queryFactory.delete(resume)
                .where(resume.resumeNo.eq(resumeNo)
                        .and(resume.memberNo.memberNo.eq(memberNo)))
                .execute();
    }

    // 이력서 존재여부 확인
    @Override
    public Long countResumeDetailByMemberAndResume(Long memberNo, Long resumeNo) {
        return queryFactory.select(resume.count())
                .from(resume)
                .where(resume.resumeNo.eq(resumeNo)
                        .and(resume.memberNo.memberNo.eq(memberNo)))
                .fetchFirst();
    }

    // 개인별 이력서 수 조회
    @Override
    public Long countResumeDetailByMember(Long memberNo) {
        return queryFactory.select(resume.count())
                .from(resume)
                .where(resume.memberNo.memberNo.eq(memberNo))
                .fetchFirst();
    }

}
