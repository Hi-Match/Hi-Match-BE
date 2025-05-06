package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum SchPart {
    GED("검정고시"),
    HIGH_SCHOOL("고등학교"),
    ASSOCIATE_DEGREE("전문학사"),
    BACHELOR_DEGREE("학사"),
    MASTER_DEGREE("석사"),
    DOCTORAL_DEGREE("박사");

    private final String columData;

    SchPart(String columData) {
        this.columData = columData;
    }

    @JsonValue
    public String getColumData() {
        return columData;
    }

    @JsonCreator
    public static SchPart fromString(String value) {
        return Arrays.stream(SchPart.values())
                .filter(schPart -> schPart.columData.equals(value))
                .findAny()
                .orElse(null);
    }
}
