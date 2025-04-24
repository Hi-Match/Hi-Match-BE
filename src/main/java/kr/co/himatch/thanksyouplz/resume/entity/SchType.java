package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum SchType {
    FRESHMAN("입학"),
    TRANSFER("편입");

    private final String columData;

    SchType(String columData) {
        this.columData = columData;
    }

    @JsonValue
    public String getColumData() {
        return columData;
    }

    @JsonCreator
    public static SchType fromString(String value) {
        return Arrays.stream(SchType.values())
                .filter(schType -> schType.columData.equals(value))
                .findAny()
                .orElse(null);
    }
}
