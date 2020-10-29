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
        // TODO: refactor later to 1 method that takes in a List of generic args
        ItemValidator.validateStringArgumentsAreNotEmpty(List.of(newItemToAdd.getName(), newItemToAdd.getDescription()));
        ItemValidator.validateNumericArgumentIsNotNegative(newItemToAdd.getPriceInEuros());
        ItemValidator.validateNumericArgumentIsNotNegative(newItemToAdd.getAmountInStock());


        IdValidator.validateSingleUUID(userId);
        if(!adminDatabase.isUserAnAdmin(userId)) throw new AdminPrivilegeException("Only an admin can add a new item!");

        itemDatabase.addNewItem(newItemToAdd);
    }
}
