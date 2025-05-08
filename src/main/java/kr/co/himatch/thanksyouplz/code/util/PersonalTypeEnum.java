package kr.co.himatch.thanksyouplz.code.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PersonalTypeEnum {
    NDCO("NDCO"),
    NDCS("NDCS"),
    NDLO("NDLO"),
    NDLS("NDLS"),
    NBCO("NBCO"),
    NBCS("NBCS"),
    NBLO("NBLO"),
    NBLS("NBLS"),
    FDCO("FDCO"),
    FDCS("FDCS"),
    FDLO("FDLO"),
    FDLS("FDLS"),
    FBCO("FBCO"),
    FBCS("FBCS"),
    FBLO("FBLO"),
    FBLS("FBLS");

    private final String columData;

    PersonalTypeEnum(String columData) {
        this.columData = columData;
    }

    @JsonValue
    public String getColumData() {
        return columData;
    }
}
