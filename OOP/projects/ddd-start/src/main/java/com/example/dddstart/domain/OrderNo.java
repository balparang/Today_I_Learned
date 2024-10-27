package com.example.dddstart.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderNo implements Serializable {

    private Long id;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderNo orderNo = (OrderNo) o;
        return Objects.equals(id, orderNo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
