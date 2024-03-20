package com.mycompany.group234.converter;

import com.mycompany.group234.enums.Position;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class PositionConverter implements AttributeConverter<Position, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Position position) {
        return position != null ? position.ordinal() : null;
    }

    @Override
    public Position convertToEntityAttribute(Integer dbData) {
      if (dbData == null) {
        return null;
      }
		  return Position.getPosition(dbData);
    }
}
