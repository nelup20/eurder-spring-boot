package com.neluplatonov.eurder.domain;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private String id;
    private List<ItemGroup> items;
    private String customerId;
    private double totalOrderCostInEuros;

    public Order(List<ItemGroup> items, String customerId, double totalOrderCostInEuros) {
        this.id = UUID.randomUUID().toString();
        this.items = items;
        this.customerId = customerId;
        this.totalOrderCostInEuros = totalOrderCostInEuros;
    }

    public String getId() {
        return id;
    }

    public List<ItemGroup> getItems() {
        return items;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getTotalOrderCostInEuros() {
        return totalOrderCostInEuros;
    }



}
