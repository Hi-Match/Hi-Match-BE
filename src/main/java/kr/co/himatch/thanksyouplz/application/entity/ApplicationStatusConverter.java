package kr.co.himatch.thanksyouplz.application.entity;

import jakarta.persistence.AttributeConverter;

public class ApplicationStatusConverter implements AttributeConverter<ApplicationStatus, String> {
    @Override
    public String convertToDatabaseColumn(ApplicationStatus armyPart) {
        if (armyPart == null) {
            return null;
        }
        return armyPart.getColumData();
    }

    @Override
    public ApplicationStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (ApplicationStatus part : ApplicationStatus.values()) {
            if (part.getColumData().equals(dbData)) {
                return part;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
