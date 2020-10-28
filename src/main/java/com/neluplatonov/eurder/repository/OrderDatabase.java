package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderDatabase {
    private Map<String, Order> orders = new HashMap<>();

    public void createOrder(Order newOrder){
        orders.put(newOrder.getId(), newOrder);
    }
}
