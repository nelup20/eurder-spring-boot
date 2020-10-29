package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderDatabase {
    private Map<String, Order> orders = new HashMap<>();

    public void createOrder(Order newOrder){
        orders.put(newOrder.getId(), newOrder);
    }

    public Map<String, Order> getAllOrders() {
        return orders;
    }

    public List<Order> getAllOrdersPerCustomer(String customerId){
        return orders.values()
                     .stream()
                     .filter(order -> order.getCustomerId().equals(customerId))
                     .collect(Collectors.toList());
    }
}
