package kr.co.himatch.thanksyouplz.bookmark.service;

import kr.co.himatch.thanksyouplz.bookmark.dto.*;

import java.util.List;

public interface BookMarkService {

    // 북마크 조회
    List<BookMarkListResponseDTO> listBookMark(BookMarkListRequestDTO bookMarkListRequestDTO, Long memberNo);

    // 북마크 등록
    BookMarkRegisterResponseDTO registerBookMark(BookMarkRegisterRequestDTO bookMarkRegisterRequestDTO, Long memberNo);

    // 북마크 삭제
    BookMarkDeleteResponseDTO deleteBookMark(BookMarkDeleteRequestDTO bookMarkDeleteRequestDTO, Long memberNo);

    // 북마크 검색
    List<BookMarkSearchResponseDTO> searchBookMark(BookMarkSearchRequestDTO bookMarkSearchRequestDTO, Long memberNo);

    // 북마크 페이지네이션
    BookMarkPageResponseDTO pageBookMark(Long memberNo);
}
