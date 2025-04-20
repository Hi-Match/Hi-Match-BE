package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonValue;

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
}
