package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.AttributeConverter;

public class ArmyPartConverter implements AttributeConverter<ArmyPart, String> {
    @Override
    public String convertToDatabaseColumn(ArmyPart armyPart) {
        if (armyPart == null) {
            return null;
        }
        return armyPart.getColumData();
    }

    @Override
    public ArmyPart convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (ArmyPart part : ArmyPart.values()) {
            if (part.getColumData().equals(dbData)) {
                return part;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
