package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SchPart {
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
}
