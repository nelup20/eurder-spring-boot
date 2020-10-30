package com.neluplatonov.eurder.api.mappers;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.CreatedItemGroupDto;
import com.neluplatonov.eurder.api.dtos.itemgroupdtos.NewItemGroupDto;
import com.neluplatonov.eurder.domain.ItemGroup;


import java.util.List;
import java.util.stream.Collectors;

public class ItemGroupMapper {

    public static List<ItemGroup> convertListOfItemGroupDtosToListOfItemGroups(List<NewItemGroupDto> newItemGroupDtosToConvert){
        return newItemGroupDtosToConvert.stream()
                                     .map(itemGroupDto -> new ItemGroup(itemGroupDto.getItemId(), itemGroupDto.getItemQuantityToOrder()))
                                     .collect(Collectors.toList());
    }

    public static List<CreatedItemGroupDto> convertListOfItemGroupsToListOfCreatedItemGroupDtos(List<ItemGroup> itemGroupsToConvert){
        return itemGroupsToConvert.stream()
                                  .map(itemGroup -> new CreatedItemGroupDto(itemGroup.getItemId(), itemGroup.getItemQuantityToOrder(), itemGroup.getShippingDate()))
                                  .collect(Collectors.toList());
    }
}
