package com.neluplatonov.eurder.api.dtos.itemDtos;

public class UpdateItemDto {
    private String name;
    private String description;
    private double priceInEuros;
    private int amountInStock;

    public UpdateItemDto(String name, String description, double priceInEuros, int amountInStock) {
        this.name = name;
        this.description = description;
        this.priceInEuros = priceInEuros;
        this.amountInStock = amountInStock;
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
}
