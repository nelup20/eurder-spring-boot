package com.neluplatonov.eurder.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class ItemGroup {
    private String itemId;
    private int itemQuantityToOrder;
    private LocalDate shippingDate;

    public ItemGroup(String itemId, int itemQuantityToOrder) {
        this.itemId = itemId;
        this.itemQuantityToOrder = itemQuantityToOrder;
        this.shippingDate = LocalDate.now().plusWeeks(1);
    }

    public String getItemId() {
        return itemId;
    }

    public int getItemQuantityToOrder() {
        return itemQuantityToOrder;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }
}
