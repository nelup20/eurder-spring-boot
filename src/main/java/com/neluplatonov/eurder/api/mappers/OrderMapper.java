package com.neluplatonov.eurder.api.mappers;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.ReportItemGroupDto;
import com.neluplatonov.eurder.api.dtos.orderdtos.NewlyCreatedOrderDto;
import com.neluplatonov.eurder.api.dtos.orderdtos.ReportOrderDto;
import com.neluplatonov.eurder.domain.ItemGroup;
import com.neluplatonov.eurder.domain.Order;
import com.neluplatonov.eurder.repository.ItemDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    // TODO: See if you can refactor this monstrosity (2nd idea: make 2 nested streams?)
    public static List<ReportOrderDto> convertCustomerOrdersListToReportOrderDtoList(List<Order> listToConvert, ItemDatabase itemDatabase){
        List<ReportOrderDto> resultOrderList = new ArrayList<>();

        for(Order order : listToConvert){
            List<ReportItemGroupDto> resultItemGroupList = new ArrayList<>();

            for(ItemGroup itemGroup : order.getItems()){
                String itemName = itemDatabase.getItemName(itemGroup.getItemId());
                int itemQuantityOrdered = itemGroup.getItemQuantityToOrder();
                double itemGroupTotalPriceInEuros = itemGroup.getItemPriceInEuros() * itemQuantityOrdered;

                resultItemGroupList.add(new ReportItemGroupDto(itemName, itemQuantityOrdered, itemGroupTotalPriceInEuros));
            }

            resultOrderList.add(new ReportOrderDto(order.getId(), resultItemGroupList, order.getTotalOrderCostInEuros()));
        }

        return resultOrderList;
    }

    public static NewlyCreatedOrderDto convertOrderToNewlyCreatedOrderDto(Order orderToConvert){
        return new NewlyCreatedOrderDto(orderToConvert.getId(), ItemGroupMapper.convertListOfItemGroupsToListOfCreatedItemGroupDtos(orderToConvert.getItems()), orderToConvert.getCustomerId(), orderToConvert.getTotalOrderCostInEuros());
    }
}
