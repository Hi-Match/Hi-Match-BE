package kr.co.himatch.thanksyouplz.code.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PersonalTypeEnum {
    NDCO("NDCO", "유쾌한 감각과 유연한 리더십으로 팀을 이끄는 나침반"),
    NDCS("NDCS", "팀 분위기를 깨우는 에너지 중심의 분위기 메이커"),
    NDLO("NDLO", "말에 무게를 더해 방향을 제시하는 소통형 전략가"),
    NDLS("NDLS", "기준은 단단히, 마음은 부드럽게 조직을 이끄는 리더"),
    NBCO("NBCO", "나누고, 듣고, 새롭게 제안하는 마르지 않는 샘"),
    NBCS("NBCS", "창의와 균형을 바탕으로 안정감 있게 길을 여는 온화한 개척자"),
    NBLO("NBLO", "이성과 감성 사이, 팀을 부드럽게 연결하는 다리"),
    NBLS("NBLS", "서두르지 않지만 놓치지 않는 팀의 뿌리"),
    FDCO("FDCO", "틀을 넘는 감각으로 변화를 앞당기는 모험가"),
    FDCS("FDCS", "몰입으로 규칙을 설계하고 변화를 만들어내는 건축가"),
    FDLO("FDLO", "복잡함 속에서 질서를 끌어내 방향과 해답을 제시하는 등불"),
    FDLS("FDLS", "사소한 시작도 끝까지 책임지는, 흔들림 없는 에너지의 불씨"),
    FBCO("FBCO", "정교한 감각과 결과로 대답하는 몰입의 실타래"),
    FBCS("FBCS", "질서 있는 흐름 속에 새로움을 틔워 팀을 키워내는 키움꾼"),
    FBLO("FBLO", "복잡한 사고의 퍼즐을 정교하게 완성하는 해석의 나사"),
    FBLS("FBLS", "매 순간 정확하고 흔들림 없이 책임을 완수하는 팀의 주춧돌");

    private final String columData;
    private final String slogan;

    PersonalTypeEnum(String columData, String slogan) {
        this.columData = columData;
        this.slogan = slogan;
    }

    @JsonValue
    public String getColumData() {
        return columData;
    }
}
