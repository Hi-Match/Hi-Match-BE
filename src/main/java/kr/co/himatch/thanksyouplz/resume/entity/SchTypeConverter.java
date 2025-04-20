package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter

public class SchTypeConverter implements AttributeConverter<SchType, String> {
    @Override
    public String convertToDatabaseColumn(SchType schType) {
        if (schType == null) {
            return null;
        }
        return schType.getColumData();
    }

    @Override
    public SchType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (SchType type : SchType.values()) {
            if (type.getColumData().equals(dbData)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
