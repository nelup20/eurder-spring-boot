package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.domain.Item;
import com.neluplatonov.eurder.exception.AdminPrivilegeException;
import com.neluplatonov.eurder.repository.AdminDatabase;
import com.neluplatonov.eurder.repository.ItemDatabase;
import com.neluplatonov.eurder.validator.IdValidator;
import com.neluplatonov.eurder.validator.ItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemService {

    private ItemDatabase itemDatabase;
    private AdminDatabase adminDatabase;

    @Autowired
    public ItemService(ItemDatabase itemDatabase, AdminDatabase adminDatabase) {
        this.itemDatabase = itemDatabase;
        this.adminDatabase = adminDatabase;
    }

    public void addNewItem(String userId, Item newItemToAdd){

        ItemValidator.validateItemArguments(List.of(newItemToAdd.getName(), newItemToAdd.getDescription(), newItemToAdd.getPriceInEuros(), newItemToAdd.getAmountInStock()));

        IdValidator.validateSingleUUID(userId);
        if(!adminDatabase.isUserAnAdmin(userId)) throw new AdminPrivilegeException("Only an admin can add a new item!");

        itemDatabase.addNewItem(newItemToAdd);
    }

    public void checkThatItemExists(String itemId){
        IdValidator.validateSingleUUID(itemId);
        if(!itemDatabase.itemExists(itemId)) throw new IllegalArgumentException("The item with the provided ID does not exist!");
    }

    // Yes the functionality is exactly the same as addNewItem since we're using a HashMap as a database
    // I could just make it into 1 method and call it "addNewItemOrUpdateExistingItem". But what about future changing requirements?
    // I'll first commit this version & then I'll make it into 1 method just so it's clear what my thought process was
    public void updateItem(String userId, Item itemToUpdate){
        ItemValidator.validateItemArguments(List.of(itemToUpdate.getName(), itemToUpdate.getDescription(), itemToUpdate.getPriceInEuros(), itemToUpdate.getAmountInStock()));

        IdValidator.validateSingleUUID(userId);
        if(!adminDatabase.isUserAnAdmin(userId)) throw new AdminPrivilegeException("Only an admin can add a new item!");

        itemDatabase.updateItem(itemToUpdate);
    }
}
