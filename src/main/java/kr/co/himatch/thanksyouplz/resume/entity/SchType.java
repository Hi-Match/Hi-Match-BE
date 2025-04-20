package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonValue;

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
}
