package com.neluplatonov.eurder.domain;

import com.neluplatonov.eurder.api.dtos.orderdtos.ReportOrderDto;

import java.util.List;

public class Report {
    private List<ReportOrderDto> orders;
    private double totalPriceOfAllOrdersInEuros;

    public Report(List<ReportOrderDto> orders) {
        this.orders = orders;
        this.totalPriceOfAllOrdersInEuros = orders.stream().map(ReportOrderDto::getTotalOrderCostInEuros).reduce(0.0, Double::sum);
    }

    public List<ReportOrderDto> getOrders() {
        return orders;
    }

    public double getTotalPriceOfAllOrdersInEuros() {
        return totalPriceOfAllOrdersInEuros;
    }
}
