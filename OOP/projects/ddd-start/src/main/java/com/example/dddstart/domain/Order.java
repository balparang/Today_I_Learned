package com.example.dddstart.domain;

import com.example.dddstart.common.jpa.MoneyConverter;
import com.example.dddstart.common.model.Money;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @EmbeddedId
    private OrderNo number; // OrderNo가 식별자 타입

    @Embedded
    private Orderer orderer;

    @Column(name = "state", columnDefinition = "varchar(30)")
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(name = "total_amounts")
    @Convert(converter = MoneyConverter.class)
    private Money totalAmounts;

    @Embedded
    private ShippingInfo shippingInfo;

    public void cancel() {

    }

    // cancel, changeShippingInfo 등 도메인 기능 구현
    // 필요한 get메서드 구현
}
