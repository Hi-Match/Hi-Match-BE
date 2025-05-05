package kr.co.himatch.thanksyouplz.resume.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.resume.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.resume.entity.QResumeCertificate.resumeCertificate;

public class ResumeCertificateRepositoryImpl implements ResumeCertificateRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    // 이력서 자격증 조회
    @Override
    public List<ResumeCertificateDTO> selectResumeCertificate(Long resumeNo) {
        return queryFactory.select(
                        Projections.constructor(ResumeCertificateDTO.class,
                                resumeCertificate.cerTitle,
                                resumeCertificate.cerIssuingAuthority,
                                resumeCertificate.cerDate,
                                resumeCertificate.cerExpire
                        )
                ).from(resumeCertificate)
                .where(resumeCertificate.resumeNo.resumeNo.eq(resumeNo))
                .fetch();
    }

    // 이력서 자격증 삭제
    @Override
    public void deleteResumeCertificate(Long resumeNo) {
        queryFactory.delete(resumeCertificate)
                .where(resumeCertificate.resumeNo.resumeNo.eq(resumeNo))
                .execute();
    }

}
