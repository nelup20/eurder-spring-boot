package com.neluplatonov.eurder.api.mappers;

import com.neluplatonov.eurder.api.dtos.itemDtos.NewItemDto;
import com.neluplatonov.eurder.api.dtos.itemDtos.UpdateItemDto;
import com.neluplatonov.eurder.domain.Item;

public class ItemMapper {

    public static Item convertNewItemDtoToItem(NewItemDto newItemDto){
        return new Item(newItemDto.getName(), newItemDto.getDescription(), newItemDto.getPriceInEuros(), newItemDto.getAmountInStock());
    }

    public static Item convertUpdateItemDtoToItem(UpdateItemDto newItemDto){
        return new Item(newItemDto.getName(), newItemDto.getDescription(), newItemDto.getPriceInEuros(), newItemDto.getAmountInStock());
    }
}
