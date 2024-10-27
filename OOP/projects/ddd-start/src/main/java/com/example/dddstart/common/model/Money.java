package com.example.dddstart.common.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

    private Integer value;

    public Money(final Integer value) {
        this.value = value;
    }
}
