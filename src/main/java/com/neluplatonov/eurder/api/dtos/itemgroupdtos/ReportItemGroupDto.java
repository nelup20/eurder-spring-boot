package com.neluplatonov.eurder.api.dtos.itemgroupdtos;

public class ReportItemGroupDto {
    private String itemName;
    private int itemQuantityOrdered;
    private double itemGroupPriceInEuros;

    public ReportItemGroupDto(String itemName, int itemQuantityOrdered, double itemGroupPriceInEuros) {
        this.itemName = itemName;
        this.itemQuantityOrdered = itemQuantityOrdered;
        this.itemGroupPriceInEuros = itemGroupPriceInEuros;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantityOrdered() {
        return itemQuantityOrdered;
    }

    public double getItemGroupPriceInEuros() {
        return itemGroupPriceInEuros;
    }
}
