package com.example.dddstart.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "purchase_order")
public class Order {

    @EmbeddedId
    private OrderNo number; // OrderNo가 식별자 타입

    @Embedded
    private Orderer orderer;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Embedded
    private ShippingInfo shippingInfo;

    public void cancel() {

    }

    // cancel, changeShippingInfo 등 도메인 기능 구현
    // 필요한 get메서드 구현


}
