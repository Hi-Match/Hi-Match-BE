package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ArmyType {
    NON_APPLICABLE("비대상"),
    APPLICABLE("대상"),
    EXEMPT("면제");

    private final String columData;

    ArmyType(String columData) {
        this.columData = columData;
    }

    @JsonValue
    public String getColumData() {
        return columData;
    }
}
