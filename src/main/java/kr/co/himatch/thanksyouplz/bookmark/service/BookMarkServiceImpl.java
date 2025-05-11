package kr.co.himatch.thanksyouplz.bookmark.service;

import jakarta.transaction.Transactional;
import kr.co.himatch.thanksyouplz.application.entity.JobPosting;
import kr.co.himatch.thanksyouplz.application.repository.JobPostingRepository;
import kr.co.himatch.thanksyouplz.bookmark.dto.*;
import kr.co.himatch.thanksyouplz.bookmark.entity.BookMark;
import kr.co.himatch.thanksyouplz.bookmark.repository.BookMarkRepository;
import kr.co.himatch.thanksyouplz.member.entity.Member;
import kr.co.himatch.thanksyouplz.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BookMarkServiceImpl implements BookMarkService{

    @Autowired
    private BookMarkRepository bookMarkRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JobPostingRepository jobPostingRepository;


    // 북마크 조회
    @Override
    public List<BookMarkListResponseDTO> listBookMark(BookMarkListRequestDTO bookMarkListRequestDTO, Long memberNo) {
        List<BookMarkListResponseDTO> selectBookMark = bookMarkRepository.selectBookMark(memberNo, bookMarkListRequestDTO.getPage());

        return selectBookMark;
    }

    // 북마크 등록
    @Override
    public BookMarkRegisterResponseDTO registerBookMark(BookMarkRegisterRequestDTO bookMarkRegisterRequestDTO, Long memberNo) {
        Member member = memberRepository.getReferenceById(memberNo);

        Long selectSameBookMark = bookMarkRepository.selectSameBookMark(bookMarkRegisterRequestDTO.getPostingNo(), memberNo);

        if (selectSameBookMark > 0){
            return null;
        }else{
            Optional<JobPosting> jobPosting = jobPostingRepository.findById(bookMarkRegisterRequestDTO.getPostingNo());

            if (jobPosting.isEmpty()){
                return null;
            }

            JobPosting posting = jobPosting.get();


            BookMark bookMark = BookMark.builder()
                    .bookMarkDate(LocalDateTime.now())
                    .memberNo(member)
                    .postingNo(posting)
                    .build();

            bookMarkRepository.save(bookMark);

            BookMarkRegisterResponseDTO bookMarkRegisterResponseDTO = new BookMarkRegisterResponseDTO();
            bookMarkRegisterResponseDTO.setMessage("Success!");

            return bookMarkRegisterResponseDTO;
        }
    }

    // 북마크 삭제
    @Override
    public BookMarkDeleteResponseDTO deleteBookMark(BookMarkDeleteRequestDTO bookMarkDeleteRequestDTO, Long memberNo) {
        bookMarkRepository.deleteBookMark(bookMarkDeleteRequestDTO.getBookMarkNo(), memberNo);

        BookMarkDeleteResponseDTO bookMarkDeleteResponseDTO = new BookMarkDeleteResponseDTO();
        bookMarkDeleteResponseDTO.setMessage("Success!");

        return bookMarkDeleteResponseDTO;
    }

    // 북마크 검색
    @Override
    public List<BookMarkSearchResponseDTO> searchBookMark(BookMarkSearchRequestDTO bookMarkSearchRequestDTO, Long memberNo) {
        log.info("북마크 검색 요청: memberNo = {}, keyword = '{}'", memberNo, bookMarkSearchRequestDTO.getKeyword());

        List<BookMarkSearchResponseDTO> selectKeywordByBookMark = bookMarkRepository.selectKeywordByBookMark(
                bookMarkSearchRequestDTO.getKeyword(),
                bookMarkSearchRequestDTO.getPage(), memberNo);

        return selectKeywordByBookMark;
    }

    // 북마크 페이지네이션
    // 1을 더하지 않는다. 나눈 뒤, 소수점 발생을 예상하여 Ceil을 사용해서 올림처리한다.
    @Override
    public BookMarkPageResponseDTO pageBookMark(Long memberNo, BookMarkPageRequestDTO bookMarkPageRequestDTO) {
        Long selectBookMarkCount = bookMarkRepository.selectBookMarkCount(memberNo, bookMarkPageRequestDTO.getKeyword());

        Long maxPage = (long)Math.ceil((double) selectBookMarkCount / 10);

        BookMarkPageResponseDTO bookMarkPageResponseDTO = new BookMarkPageResponseDTO();
        bookMarkPageResponseDTO.setMaxPage(maxPage);

        return bookMarkPageResponseDTO;
    }

}
