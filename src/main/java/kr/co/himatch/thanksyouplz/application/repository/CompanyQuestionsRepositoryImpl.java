package kr.co.himatch.thanksyouplz.application.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.dto.ApplicationCompanySelectListResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.application.entity.QCompanyQuestions.companyQuestions;

public class CompanyQuestionsRepositoryImpl implements CompanyQuestionsRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<ApplicationCompanySelectListResponseDTO> selectQuestionByPostingNo(Long postingNo) {
        return queryFactory.select(
                        Projections.constructor(ApplicationCompanySelectListResponseDTO.class, companyQuestions.questionTitle, companyQuestions.questionLength)
                )
                .from(companyQuestions)
                .where(companyQuestions.postingNo.postingNo.eq(postingNo))
                .fetch();
    }
}
