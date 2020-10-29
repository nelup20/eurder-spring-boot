package com.neluplatonov.eurder.api.dtos.itemgroupdtos;


public class ItemGroupDto {
    private String itemId;
    private int itemQuantityToOrder;

    public ItemGroupDto(String itemId, int itemQuantityToOrder) {
        this.itemId = itemId;
        this.itemQuantityToOrder = itemQuantityToOrder;
    }

    public String getItemId() {
        return itemId;
    }

    public int getItemQuantityToOrder() {
        return itemQuantityToOrder;
    }
}
