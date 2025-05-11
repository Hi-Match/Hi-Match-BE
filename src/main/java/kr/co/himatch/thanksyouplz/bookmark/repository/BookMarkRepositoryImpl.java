package kr.co.himatch.thanksyouplz.bookmark.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.himatch.thanksyouplz.application.entity.QJobPosting;
import kr.co.himatch.thanksyouplz.bookmark.dto.BookMarkListResponseDTO;
import kr.co.himatch.thanksyouplz.bookmark.dto.BookMarkSearchResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kr.co.himatch.thanksyouplz.bookmark.entity.QBookMark.*;
import static kr.co.himatch.thanksyouplz.application.entity.QJobPosting.*;
import static kr.co.himatch.thanksyouplz.company.entity.QCompany.*;

public class BookMarkRepositoryImpl implements BookMarkRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;


    // 북마크 조회
    @Override
    public List<BookMarkListResponseDTO> selectBookMark(Long memberNo, Long page) {
        return queryFactory.select(Projections.constructor(BookMarkListResponseDTO.class, bookMark.bookMarkNo,
                        bookMark.postingNo.postingNo, bookMark.postingNo.companyNo.companyImgA,
                        bookMark.postingNo.companyNo.companyName, bookMark.postingNo.postingType,
                        bookMark.postingNo.postingTitle, bookMark.postingNo.companyNo.companyAddress,
                        bookMark.postingNo.postingEducation, bookMark.postingNo.postingDeadline))
                .from(bookMark)
                .where(bookMark.memberNo.memberNo.eq(memberNo))
                .limit(10)
                .offset(page * 10)
                .fetch();
    }

    // 북마크 등록 전, Count를 사용해 이미 해당 공고를 북마크했는지 체크한다.
    // 0이 아니라면 중복이므로 null return 진행
    @Override
    public Long selectSameBookMark(Long postingNo, Long memberNo) {
        return queryFactory.select(bookMark.count())
                .from(bookMark)
                .where(bookMark.postingNo.postingNo.eq(postingNo), bookMark.memberNo.memberNo.eq(memberNo))
                .fetchFirst();
    }

    // 북마크 삭제
    @Override
    public void deleteBookMark(Long bookMarkNo, Long memberNo) {
        queryFactory.delete(bookMark)
                .where(bookMark.bookMarkNo.eq(bookMarkNo).and(bookMark.memberNo.memberNo.eq(memberNo)))
                .execute();
    }

    // 북마크 검색
    @Override
    public List<BookMarkSearchResponseDTO> selectKeywordByBookMark(String keyword, Long page, Long memberNo) {
        return queryFactory.select(Projections.constructor(BookMarkSearchResponseDTO.class,
                        bookMark.bookMarkNo, bookMark.postingNo.postingNo,
                        bookMark.postingNo.companyNo.companyImgA, bookMark.postingNo.companyNo.companyName,
                        bookMark.postingNo.postingType, bookMark.postingNo.postingTitle,
                        bookMark.postingNo.companyNo.companyAddress, bookMark.postingNo.postingEducation,
                        bookMark.postingNo.postingDeadline))
                .from(bookMark)
                .join(bookMark.postingNo, jobPosting)
                .join(jobPosting.companyNo, company)
                .where(bookMark.memberNo.memberNo.eq(memberNo)
                        .and(bookMark.postingNo.postingTitle.like("%" + keyword + "%")
                                .or(bookMark.postingNo.companyNo.companyName.like("%" + keyword + "%"))))
                .limit(10)
                .offset(page * 10)
                .orderBy(bookMark.bookMarkNo.desc())
                .fetch();
    }

    // 북마크 페이지네이션(몇 페이지까지 있는지?)
    // Ceil을 사용해서 소수점이 생길 경우 올림처리해서 보내준다.
    @Override
    public Long selectBookMarkCount(Long memberNo) {
        return queryFactory.select(bookMark.bookMarkNo.count())
                .from(bookMark)
                .where(bookMark.memberNo.memberNo.eq(memberNo))
                .fetchFirst();
    }
}