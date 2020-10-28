package com.neluplatonov.eurder.domain;

import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private List<ItemGroup> items;
    private String customerId;

    public Order(List<ItemGroup> items, String customerId) {
        this.id = UUID.randomUUID().toString();
        this.items = items;
        this.customerId = customerId;
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
}
