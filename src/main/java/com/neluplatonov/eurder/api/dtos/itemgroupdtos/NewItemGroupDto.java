package com.neluplatonov.eurder.api.dtos.itemgroupdtos;


public class NewItemGroupDto {
    private String itemId;
    private int itemQuantityToOrder;

    public NewItemGroupDto(String itemId, int itemQuantityToOrder) {
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
