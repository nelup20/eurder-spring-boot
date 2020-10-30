package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.NewItemGroupDto;
import com.neluplatonov.eurder.api.dtos.orderdtos.ReportOrderDto;
import com.neluplatonov.eurder.api.mappers.OrderMapper;
import com.neluplatonov.eurder.domain.ItemGroup;
import com.neluplatonov.eurder.domain.Order;
import com.neluplatonov.eurder.domain.Report;
import com.neluplatonov.eurder.exception.NoCustomerFoundException;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import com.neluplatonov.eurder.repository.ItemDatabase;
import com.neluplatonov.eurder.repository.OrderDatabase;
import com.neluplatonov.eurder.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        if(!customerDatabase.customerExists(customerId)) throw new NoCustomerFoundException("The customer with the provided ID does not exist! Only a registered Eurder customer can make an Order.");

        List<ItemGroup> orderItemsWithCorrectShippingDatesAndPricesAndItemNames = assignCorrectShippingDatesAndItemPricesAndItemNames(orderItems);
        double newOrderTotalCostInEuros = calculateTotalCostInEurosForNewOrder(orderItemsWithCorrectShippingDatesAndPricesAndItemNames);

        Order newOrder = new Order(orderItemsWithCorrectShippingDatesAndPricesAndItemNames, customerId, newOrderTotalCostInEuros);
        orderDatabase.createOrder(newOrder);

        return newOrder;
    }


    public void checkIfAllItemIdsExistInItemDatabase(List<NewItemGroupDto> newItemGroupDtosToCheck){
        IdValidator.validateListOfUUIDs(newItemGroupDtosToCheck.stream().map(NewItemGroupDto::getItemId).collect(Collectors.toList()));

        if(!newItemGroupDtosToCheck.stream().allMatch(itemGroupDto -> itemDatabase.itemExists(itemGroupDto.getItemId()))) throw new IllegalArgumentException("We couldn't find an item with 1 or more of the item ID's you provided.");
    }


    private List<ItemGroup> assignCorrectShippingDatesAndItemPricesAndItemNames(List<ItemGroup> orderItems){
        List<ItemGroup> resultList = orderItems;
        
        for(ItemGroup itemGroup : resultList){
            String itemId = itemGroup.getItemId();

            if(thereIsEnoughInStockForTheOrder(itemGroup)){
                itemGroup.setShippingDate(LocalDate.now().plusDays(1));
            }

            itemGroup.setItemPriceInEuros(itemDatabase.getItemPriceInEuros(itemId));
            itemGroup.setItemName(itemDatabase.getItemName(itemId));
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
        if(!customerDatabase.customerExists(customerId)) throw new NoCustomerFoundException("The customer with the provided ID does not exist!");

        List<Order> customerOrders = orderDatabase.getAllOrdersPerCustomer(customerId);
        List<ReportOrderDto> customerReportOrders = OrderMapper.convertCustomerOrdersListToReportOrderDtoList(customerOrders);

        return new Report(customerReportOrders);
    }

}
