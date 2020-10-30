package com.neluplatonov.eurder.domain;


import java.time.LocalDate;

public class ItemGroup {
    private String itemId;
    private int itemQuantityToOrder;
    private double itemPriceInEuros;
    private LocalDate shippingDate;

    public ItemGroup(String itemId, int itemQuantityToOrder) {
        this.itemId = itemId;
        this.itemQuantityToOrder = itemQuantityToOrder;
        this.itemPriceInEuros = 0;
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

    public double getItemPriceInEuros() {
        return itemPriceInEuros;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setItemPriceInEuros(double itemPriceInEuros) {
        this.itemPriceInEuros = itemPriceInEuros;
    }
}
