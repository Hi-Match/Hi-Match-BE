package kr.co.himatch.thanksyouplz.resume.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter

public class SchPartConverter implements AttributeConverter<SchPart, String> {
    @Override
    public String convertToDatabaseColumn(SchPart schType) {
        if (schType == null) {
            return null;
        }
        return schType.getColumData();
    }

    @Override
    public SchPart convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (SchPart type : SchPart.values()) {
            if (type.getColumData().equals(dbData)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
