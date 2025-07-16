package kr.co.himatch.thanksyouplz.exception.bookmark;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookMarkNotFoundException extends RuntimeException{
    private final String errorCode;

    public BookMarkNotFoundException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
