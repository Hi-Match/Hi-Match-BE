package kr.co.himatch.thanksyouplz.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ErrorResponse extends RuntimeException {

    private int statusCode;

    private HttpStatus httpStatus;

    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.statusCode = status.value();
        this.message = message;
        this.httpStatus = status;
    }

}