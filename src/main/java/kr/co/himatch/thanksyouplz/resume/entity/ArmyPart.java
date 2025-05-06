package kr.co.himatch.thanksyouplz.resume.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ArmyPart {
    ARMY("육군"),
    NAVY("해군"),
    AIR_FORCE("공군"),
    MARINES("해병"),
    RIOT_POLICE("전경"),
    POLICE_SERVICE("의경"),
    COAST_GUARD("해경"),
    TECH_SERVICE("병역특례"),
    PUBLIC_SERVICE("공익"),
    KATUSA("카투사"),
    FIRE_SERVICE("의무소방"),
    OTHER("기타");

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

/*

* */