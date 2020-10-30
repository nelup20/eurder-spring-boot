package com.neluplatonov.eurder.api.dtos.itemgroupdtos;

import java.time.LocalDate;

public class CreatedItemGroupDto {
    private String itemId;
    private int itemQuantityToOrder;
    private LocalDate shippingDate;

    public CreatedItemGroupDto(String itemId, int itemQuantityToOrder, LocalDate shippingDate) {
        this.itemId = itemId;
        this.itemQuantityToOrder = itemQuantityToOrder;
        this.shippingDate = shippingDate;
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
}
