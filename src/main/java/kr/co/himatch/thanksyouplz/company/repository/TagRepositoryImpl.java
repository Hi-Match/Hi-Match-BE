package kr.co.himatch.thanksyouplz.company.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.company.dto.CompanyInfoTagResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.company.entity.QTag.*;

public class TagRepositoryImpl implements TagRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<CompanyInfoTagResponseDTO> selectTags() {
        return queryFactory.select(
                        Projections.constructor(CompanyInfoTagResponseDTO.class, tag.tagName)
                ).from(tag)
                .fetch();
    }
}
