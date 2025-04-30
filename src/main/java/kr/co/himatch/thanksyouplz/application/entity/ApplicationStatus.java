package kr.co.himatch.thanksyouplz.application.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ApplicationStatus {
    TOTAL("TOTAL"),
    SUBMIT("SUBMIT"),
    PROGRESS("PROGRESS"),
    RESUME_PASS("RESUME_PASS"),
    FINAL_PASS("FINAL_PASS"),
    FAIL("FAIL");

    private final String columData;

    ApplicationStatus(String columData) {
        this.columData = columData;
    }

    @JsonValue
    public String getColumData() {
        return columData;
    }

    @JsonCreator
    public static ApplicationStatus fromString(String value) {
        return Arrays.stream(ApplicationStatus.values())
                .filter(ApplicationStatus -> ApplicationStatus.columData.equals(value))
                .findAny()
                .orElse(null);
    }

    public static ApplicationStatus toUpperCase(String statue){
        return Arrays.stream(ApplicationStatus.values())
                .filter(ApplicationStatus -> ApplicationStatus.columData.equals(statue.toUpperCase()))
                .findAny()
                .orElse(null);
    }
}
