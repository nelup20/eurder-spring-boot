package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.ItemGroupDto;
import com.neluplatonov.eurder.api.dtos.itemgroupdtos.ReportItemGroupDto;
import com.neluplatonov.eurder.api.dtos.orderdtos.ReportOrderDto;
import com.neluplatonov.eurder.domain.ItemGroup;
import com.neluplatonov.eurder.domain.Order;
import com.neluplatonov.eurder.domain.Report;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import com.neluplatonov.eurder.repository.ItemDatabase;
import com.neluplatonov.eurder.repository.OrderDatabase;
import com.neluplatonov.eurder.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderService {
    private OrderDatabase orderDatabase;
    private CustomerDatabase customerDatabase;
    private ItemDatabase itemDatabase;

    @Autowired
    public OrderService(OrderDatabase orderDatabase, CustomerDatabase customerDatabase, ItemDatabase itemDatabase) {
        this.orderDatabase = orderDatabase;
        this.customerDatabase = customerDatabase;
        this.itemDatabase = itemDatabase;
    }

    public Order createOrder(String customerId, List<ItemGroup> orderItems){
        IdValidator.validateSingleUUID(customerId);
        if(!customerDatabase.customerExists(customerId)) throw new IllegalArgumentException("The customer with the provided ID does not exist! Only a registered Eurder customer can make an Order.");

        List<ItemGroup> orderItemsWithCorrectShippingDates = assignCorrectShippingDates(orderItems);
        double newOrderTotalCostInEuros = calculateTotalCostInEurosForNewOrder(orderItemsWithCorrectShippingDates);

        Order newOrder = new Order(orderItemsWithCorrectShippingDates, customerId, newOrderTotalCostInEuros);
        orderDatabase.createOrder(newOrder);

        return newOrder;
    }


    public void checkIfAllItemIdsExistInItemDatabase(List<ItemGroupDto> itemGroupDtosToCheck){
        IdValidator.validateListOfUUIDs(itemGroupDtosToCheck.stream().map(ItemGroupDto::getItemId).collect(Collectors.toList()));

        if(!itemGroupDtosToCheck.stream().allMatch(itemGroupDto -> itemDatabase.itemExists(itemGroupDto.getItemId()))) throw new IllegalArgumentException("We couldn't find an item with 1 or more of the item ID's you provided.");
    }


    private List<ItemGroup> assignCorrectShippingDates(List<ItemGroup> orderItems){
        List<ItemGroup> resultList = orderItems;
        
        for(ItemGroup itemGroup : resultList){
            if(thereIsEnoughInStockForTheOrder(itemGroup)){
                itemGroup.setShippingDate(LocalDate.now().plusDays(1));
            }
        }

        return resultList;
    }


    private double calculateTotalCostInEurosForNewOrder(List<ItemGroup> orderItems){
        return orderItems.stream()
                         .map(itemGroup -> itemDatabase.getItemPriceInEuros(itemGroup.getItemId()) * itemGroup.getItemQuantityToOrder())
                         .reduce(0.0, Double::sum);
    }

    private boolean thereIsEnoughInStockForTheOrder(ItemGroup itemGroup) {
        return itemDatabase.getItemAmountInStock(itemGroup.getItemId()) >= itemGroup.getItemQuantityToOrder();
    }

    public Report getOrdersReport(String customerId){
        IdValidator.validateSingleUUID(customerId);
        if(!customerDatabase.customerExists(customerId)) throw new IllegalArgumentException("The customer with the provided ID does not exist!");

        List<Order> customerOrders = orderDatabase.getAllOrdersPerCustomer(customerId);
        List<ReportOrderDto> customerReportOrders = convertCustomerOrdersListToReportOrderDtoList(customerOrders);

        return new Report(customerReportOrders);
    }

    // TODO: See if you can refactor this monstrosity (1st idea: put it in a mapper, 2nd idea: make 2 nested streams?)
    public List<ReportOrderDto> convertCustomerOrdersListToReportOrderDtoList(List<Order> listToConvert){
        List<ReportOrderDto> resultOrderList = new ArrayList<>();

        for(Order order : listToConvert){
            List<ReportItemGroupDto> resultItemGroupList = new ArrayList<>();

            for(ItemGroup itemGroup : order.getItems()){
                String itemName = itemDatabase.getItemName(itemGroup.getItemId());
                int itemQuantityOrdered = itemGroup.getItemQuantityToOrder();
                double itemGroupTotalPriceInEuros = itemDatabase.getItemPriceInEuros(itemGroup.getItemId()) * itemQuantityOrdered;

                resultItemGroupList.add(new ReportItemGroupDto(itemName, itemQuantityOrdered, itemGroupTotalPriceInEuros));
            }

            resultOrderList.add(new ReportOrderDto(order.getId(), resultItemGroupList, order.getTotalOrderCostInEuros()));
        }

        return resultOrderList;
    }
}
