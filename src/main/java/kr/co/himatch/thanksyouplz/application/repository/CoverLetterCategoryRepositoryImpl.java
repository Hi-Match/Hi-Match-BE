package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationMemberQuestionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QCoverLetterCategory.coverLetterCategory;

public class CoverLetterCategoryRepositoryImpl implements CoverLetterCategoryRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ApplicationMemberQuestionResponseDTO> selectQuestionList() {
        return queryFactory.select(
                        Projections.constructor(ApplicationMemberQuestionResponseDTO.class, coverLetterCategory.coverCategoryTitle)
                )
                .from(coverLetterCategory)
                .fetch();
    }
}
