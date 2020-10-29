package com.neluplatonov.eurder.domain;

import java.util.UUID;

public class Item {
    private String id;
    private String name;
    private String description;
    private double priceInEuros;
    private int amountInStock;

    public Item(String name, String description, double priceInEuros, int amountInStock) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.priceInEuros = priceInEuros;
        this.amountInStock = amountInStock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPriceInEuros() {
        return priceInEuros;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priceInEuros='" + priceInEuros + '\'' +
                ", amountInStock=" + amountInStock +
                '}';
    }
}
