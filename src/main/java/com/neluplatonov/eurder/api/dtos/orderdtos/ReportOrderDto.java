package com.neluplatonov.eurder.api.dtos.orderdtos;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.ReportItemGroupDto;
import com.neluplatonov.eurder.domain.ItemGroup;

import java.util.List;
import java.util.UUID;

public class ReportOrderDto {

    private String id;
    private List<ReportItemGroupDto> items;
    private double totalOrderCostInEuros;

    public ReportOrderDto(String orderId, List<ReportItemGroupDto> items, double totalOrderCostInEuros) {
        this.id = orderId;
        this.items = items;
        this.totalOrderCostInEuros = totalOrderCostInEuros;
    }

    public String getId() {
        return id;
    }

    public List<ReportItemGroupDto> getItems() {
        return items;
    }

    public double getTotalOrderCostInEuros() {
        return totalOrderCostInEuros;
    }



}
