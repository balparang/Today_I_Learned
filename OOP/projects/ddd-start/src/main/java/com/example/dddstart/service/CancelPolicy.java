package com.example.dddstart.service;

import com.example.dddstart.domain.Order;

public class CancelPolicy {

    public boolean hasCancellationPermission(final Order order, final Canceller canceller) {
        return false;
    }
}
