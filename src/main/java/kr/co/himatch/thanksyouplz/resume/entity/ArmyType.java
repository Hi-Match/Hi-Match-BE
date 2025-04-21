package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

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

    @JsonCreator
    public static ArmyType fromString(String value) {
        return Arrays.stream(ArmyType.values())
                .filter(armyType -> armyType.columData.equals(value))
                .findAny()
                .orElse(null);
    }
}
