package kr.co.himatch.thanksyouplz.exception.member;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{
    private final String errorCode;

    public MemberNotFoundException(String errorCode, String message){
        super(message);

        this.errorCode = errorCode;
    }
}
