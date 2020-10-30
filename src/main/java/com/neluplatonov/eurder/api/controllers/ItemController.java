package com.neluplatonov.eurder.api.controllers;

import com.neluplatonov.eurder.api.dtos.itemDtos.NewItemDto;
import com.neluplatonov.eurder.api.dtos.itemDtos.UpdateItemDto;
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


    // Story 2 - Add an item
    @PostMapping
    public Item addNewItem(@RequestHeader String userId, @RequestBody NewItemDto newItemDto){
        Item newItem = ItemMapper.convertNewItemDtoToItem(newItemDto);

        itemService.addNewItemOrUpdateExistingOne(userId, newItem);

        return newItem;
    }

    // Story 4 - Update an item
    @PutMapping("/{itemId}")
    public Item updateItem(@PathVariable String itemId, @RequestHeader String userId, @RequestBody UpdateItemDto updateItemDto){
        itemService.checkThatItemExists(itemId);

        Item itemToUpdate = ItemMapper.convertUpdateItemDtoToItem(updateItemDto);
        itemToUpdate.setId(itemId);

        itemService.addNewItemOrUpdateExistingOne(userId, itemToUpdate);

        return itemToUpdate;
    }
}
