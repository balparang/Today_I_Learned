package com.example.dddstart.service;

import com.example.dddstart.domain.Order;
import com.example.dddstart.domain.OrderNo;
import com.example.dddstart.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelOrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void cancel(OrderNo orderNo, Canceller canceller) {
        final Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new NoOrderException());

        final CancelPolicy cancelPolicy = new CancelPolicy();

        if (!cancelPolicy.hasCancellationPermission(order, canceller)) {
            throw new NoCancellablePermission();
        }

        order.cancel();
    }
}
