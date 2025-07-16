package kr.co.himatch.thanksyouplz.exception;

import kr.co.himatch.thanksyouplz.exception.bookmark.BookMarkNotFoundException;
import kr.co.himatch.thanksyouplz.exception.member.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. 멤버를 못찾았을 때
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> memberNotFound(MemberNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    // 2. 북마크를 찾지 못했을 때
    @ExceptionHandler(BookMarkNotFoundException.class)
    public ResponseEntity<ErrorResponse> bookMarkNotFound(BookMarkNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // 2. 그 외 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return new ResponseEntity<>("서버에 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
}
