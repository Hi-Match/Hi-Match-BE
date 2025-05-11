package kr.co.himatch.thanksyouplz.bookmark.repository;

import kr.co.himatch.thanksyouplz.bookmark.dto.BookMarkListResponseDTO;
import kr.co.himatch.thanksyouplz.bookmark.dto.BookMarkSearchResponseDTO;

import java.util.List;

public interface BookMarkRepositoryCustom {

    // 북마크 조회
    List<BookMarkListResponseDTO> selectBookMark(Long memberNo, Long page);

    // 북마크 등록 전, Count를 사용해 이미 해당 공고를 북마크했는지 체크한다.
    // 0이 아니라면 중복이므로 null return 진행
    Long selectSameBookMark(Long postingNo, Long memberNo);

    // 북마크 삭제
    void deleteBookMark(Long bookMarkNo, Long memberNo);

    // 북마크 검색
    List<BookMarkSearchResponseDTO> selectKeywordByBookMark(String keyword, Long page, Long memberNo);

    // 북마크 페이지네이션(몇 페이지까지 있는지?)
    // Ceil을 사용해서 소수점이 생길 경우 올림처리해서 보내준다.
    Long selectBookMarkCount(Long memberNo, String keyword);

}
