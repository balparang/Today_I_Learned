package com.example.dddstart.common.jpa;

import com.example.dddstart.common.model.Money;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * AttributeConverter: 두 개 이상의 프로퍼티를 가진 밸류 타입을 한 개 컬럼에 매핑할 때 사용.
 */
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
