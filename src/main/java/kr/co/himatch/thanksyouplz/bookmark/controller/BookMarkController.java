package kr.co.himatch.thanksyouplz.bookmark.controller;

import kr.co.himatch.thanksyouplz.bookmark.dto.*;
import kr.co.himatch.thanksyouplz.bookmark.service.BookMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/bookmark")
public class BookMarkController {

    @Autowired
    private BookMarkService bookMarkService;

    // 북마크 조회
    @GetMapping("/list")
    public ResponseEntity<?> listBookMark(@ModelAttribute BookMarkListRequestDTO bookMarkListRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        if (bookMarkListRequestDTO.getPage() == null || bookMarkListRequestDTO.getPage() <= 0) {
            bookMarkListRequestDTO.setPage(0L);
        } else {
            Long page = bookMarkListRequestDTO.getPage() - 1L;
            bookMarkListRequestDTO.setPage(page);
        }

        List<BookMarkListResponseDTO> listBookMark = bookMarkService.listBookMark(bookMarkListRequestDTO, memberNo);

        if (listBookMark == null) {
            return new ResponseEntity<>("잘못된 접근입니다. 다시 시도하세요", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listBookMark, HttpStatus.OK);
        }
    }

    // 북마크 등록
    @PostMapping("/register")
    public ResponseEntity<?> registerBookMark(@RequestBody BookMarkRegisterRequestDTO bookMarkRegisterRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        BookMarkRegisterResponseDTO registerBookMark = bookMarkService.registerBookMark(bookMarkRegisterRequestDTO, memberNo);

        if (registerBookMark == null) {
            return new ResponseEntity<>("이미 등록된 공고이거나 잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(registerBookMark, HttpStatus.OK);
        }
    }

    // 북마크 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBookMark(@ModelAttribute BookMarkDeleteRequestDTO bookMarkDeleteRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        BookMarkDeleteResponseDTO deleteBookMark = bookMarkService.deleteBookMark(bookMarkDeleteRequestDTO, memberNo);

        if (deleteBookMark == null) {
            return new ResponseEntity<>("잘못된 접근입니다. 삭제할 수 없습니다.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(deleteBookMark, HttpStatus.OK);
        }
    }

    // 북마크 검색
    @PostMapping("/search")
    public ResponseEntity<?> searchBookMark(@RequestBody BookMarkSearchRequestDTO bookMarkSearchRequestDTO) {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        if (bookMarkSearchRequestDTO.getPage() == null || bookMarkSearchRequestDTO.getPage() <= 0) {
            bookMarkSearchRequestDTO.setPage(0L);
        } else {
            Long page = bookMarkSearchRequestDTO.getPage() - 1L;
            bookMarkSearchRequestDTO.setPage(page);
        }

        List<BookMarkSearchResponseDTO> searchBookMark = bookMarkService.searchBookMark(bookMarkSearchRequestDTO, memberNo);

        return new ResponseEntity<>(searchBookMark, HttpStatus.OK);
    }

    // 북마크 페이지네이션
    @GetMapping("/page")
    public ResponseEntity<?> pageBookMark() {
        Long memberNo = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        BookMarkPageResponseDTO pageBookMark = bookMarkService.pageBookMark(memberNo);

        return new ResponseEntity<>(pageBookMark, HttpStatus.OK);
    }


}
