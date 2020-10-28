package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Item;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemDatabase {
    private Map<String, Item> items = new HashMap<>();

    public ItemDatabase() {
        Item initialItem = new Item("Water", "Very refreshing bottler of water", 5, 12);
        initialItem.setId("44492ce0-dfca-49f5-b519-0bf2839f2d64");
        items.put(initialItem.getId(), initialItem);
    }

    public void addNewItem(Item newItemToAdd){
        items.put(newItemToAdd.getId(), newItemToAdd);
    }

    public Item getItemById(String itemId){
        return items.get(itemId);
    }

    public boolean itemExists(String itemId){
        return items.get(itemId) != null;
    }
}
