package com.neluplatonov.eurder.api.dtos.orderdtos;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.CreatedItemGroupDto;
import com.neluplatonov.eurder.api.dtos.itemgroupdtos.NewItemGroupDto;

import java.util.List;

public class NewlyCreatedOrderDto {
    private String id;
    private List<CreatedItemGroupDto> items;
    private String customerId;
    private double totalOrderCostInEuros;

    public NewlyCreatedOrderDto(String id, List<CreatedItemGroupDto> items, String customerId, double totalOrderCostInEuros) {
        this.id = id;
        this.items = items;
        this.customerId = customerId;
        this.totalOrderCostInEuros = totalOrderCostInEuros;
    }

    public String getId() {
        return id;
    }

    public List<CreatedItemGroupDto> getItems() {
        return items;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getTotalOrderCostInEuros() {
        return totalOrderCostInEuros;
    }
}
