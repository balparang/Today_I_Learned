package com.example.dddstart.common.jpa;

import com.example.dddstart.common.model.Money;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true) // 모든 Money 타입의 프로퍼티에 MoneyConverter 자동으로 적용
public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Money money) {
        return money == null ? null : money.getValue();
    }

    @Override
    public Money convertToEntityAttribute(final Integer value) {
        return value == null ? null : new Money(value);
    }
}
