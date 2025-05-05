package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanyQuestionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QCoverLetterCategory.coverLetterCategory;

public class CoverLetterCategoryRepositoryImpl implements CoverLetterCategoryRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    // 기업에서 선택할 자기소개서 조회
    @Override
    public List<ApplicationCompanyQuestionResponseDTO> selectQuestionList() {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanyQuestionResponseDTO.class, coverLetterCategory.coverCategoryTitle)
                )
                .from(coverLetterCategory)
                .fetch();
    }
}
