package com.neluplatonov.eurder.api.controllers;

import com.neluplatonov.eurder.api.dtos.itemDtos.NewItemDto;
import com.neluplatonov.eurder.api.mappers.ItemMapper;
import com.neluplatonov.eurder.domain.Item;
import com.neluplatonov.eurder.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item addNewItem(@RequestHeader String userId, @RequestBody NewItemDto newItemDto){
        Item newItem = ItemMapper.convertNewItemDtoToItem(newItemDto);

        itemService.addNewItem(userId, newItem);

        return newItem;
    }
}
