package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Item;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemDatabase {
    private Map<String, Item> items = new HashMap<>();

    public void addNewItem(Item newItemToAdd){
        items.put(newItemToAdd.getId(), newItemToAdd);
    }

    public Item getItemById(String itemId){
        return items.get(itemId);
    }
}
