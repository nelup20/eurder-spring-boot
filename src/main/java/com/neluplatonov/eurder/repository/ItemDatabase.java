package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Item;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemDatabase {
    private Map<String, Item> items = new HashMap<>();

    public ItemDatabase() {
        // Populating/Seeding initial database
        Item initialItem1 = new Item("Water", "Very refreshing bottler of water", 5, 12);
        initialItem1.setId("44492ce0-dfca-49f5-b519-0bf2839f2d64");
        items.put(initialItem1.getId(), initialItem1);

        Item initialItem2 = new Item("Bread", "Wow ye", 1, 30);
        initialItem2.setId("bc23cbd0-fc7a-404d-a473-39711a0f7c7c");
        items.put(initialItem2.getId(), initialItem2);

        Item initialItem3 = new Item("Milk", "Cool", 4.72, 20);
        initialItem3.setId("b79533d1-3b13-47e8-9efb-7c96bb9245c4");
        items.put(initialItem3.getId(), initialItem3);

        Item initialItem4 = new Item("Cucumbers", "Green", 50, 10);
        initialItem4.setId("c0b6efc9-ed65-448d-a06e-21a1ed4b48c8");
        items.put(initialItem4.getId(), initialItem4);
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
