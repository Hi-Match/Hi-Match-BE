package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter

public class ArmyTypeConverter implements AttributeConverter<ArmyType, String> {
    @Override
    public String convertToDatabaseColumn(ArmyType armyType) {
        if (armyType == null) {
            return null;
        }
        return armyType.getColumData();
    }

    @Override
    public ArmyType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (ArmyType type : ArmyType.values()) {
            if (type.getColumData().equals(dbData)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
