package com.example.dddstart.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    private String zipCode;
    private String address1;
    private String address2;

    protected Address() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Address address = (Address) o;
        return Objects.equals(zipCode, address.zipCode) && Objects.equals(address1, address.address1) && Objects.equals(address2, address.address2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, address1, address2);
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                '}';
    }
}
