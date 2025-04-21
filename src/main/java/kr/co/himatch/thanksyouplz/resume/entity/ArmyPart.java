package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ArmyPart {
    AIR_FORCE("공군"),
    ARMY("육군"),
    NAVY("해군"),
    MARINE_CORPS("해병대");

    private final String columData;

    ArmyPart(String columData) {
        this.columData = columData;
    }
    @JsonValue
    public String getColumData() {
        return columData;
    }

    @JsonCreator
    public static ArmyPart fromString(String value) {
        return Arrays.stream(ArmyPart.values())
                .filter(armyPart -> armyPart.columData.equals(value))
                .findAny()
                .orElse(null);
    }
}
