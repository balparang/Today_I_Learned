package com.example.dddstart.domain;

import jakarta.persistence.*;

@Embeddable
public class ShippingInfo {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zipcode")),
            @AttributeOverride(name = "address1", column = @Column(name = "shipping_address1")),
            @AttributeOverride(name = "address2", column = @Column(name = "shipping_address2"))
    })
    private Address address;

    protected ShippingInfo() {
    }
}
