package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberDetailListResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QCoverLetter.coverLetter;

public class CoverLetterRepositoryImpl implements CoverLetterRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ApplicationMemberDetailListResponseDTO> selectQuestionListByApplicationNo(Long applicationNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationMemberDetailListResponseDTO.class, coverLetter.coverQuestion, coverLetter.coverContent)
                )
                .from(coverLetter)
                .where(coverLetter.applicationNo.applicationNo.eq(applicationNo))
                .fetch();
    }
}
