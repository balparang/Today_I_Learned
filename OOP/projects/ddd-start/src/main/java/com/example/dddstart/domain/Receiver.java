package com.example.dddstart.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Receiver {

    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_phone")
    private String phone;

    protected Receiver() {
    }

    public Receiver(final String name, final String phone) {
        this.name = name;
        this.phone = phone;
    }
}
