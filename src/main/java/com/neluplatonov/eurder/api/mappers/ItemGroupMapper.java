package com.neluplatonov.eurder.api.mappers;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.ItemGroupDto;
import com.neluplatonov.eurder.domain.ItemGroup;


import java.util.List;
import java.util.stream.Collectors;

public class ItemGroupMapper {

    public static List<ItemGroup> convertListOfItemGroupDtosToListOfItemGroups(List<ItemGroupDto> itemGroupDtosToConvert){
        return itemGroupDtosToConvert.stream()
                                     .map(itemGroupDto -> new ItemGroup(itemGroupDto.getItemId(), itemGroupDto.getItemQuantityToOrder()))
                                     .collect(Collectors.toList());
    }
}
