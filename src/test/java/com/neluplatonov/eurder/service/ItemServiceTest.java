package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.domain.Item;
import com.neluplatonov.eurder.exception.AdminPrivilegeException;
import com.neluplatonov.eurder.repository.AdminDatabase;
import com.neluplatonov.eurder.repository.ItemDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    @Test
    void givenNewItemService_whenAdding1NewItemWithInvalidAdminId_thenThrowsIllegalArgumentException(){
        //given
        ItemDatabase itemDatabase = new ItemDatabase();
        AdminDatabase adminDatabase = new AdminDatabase();
        ItemService itemService = new ItemService(itemDatabase, adminDatabase);

        //when
        Item newItem = new Item("Milk", "Nice for bones", 50, 50);
        String adminId = "123456";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            itemService.addNewItem(adminId, newItem);
        });

    }

    @Test
    void givenNewItemService_whenAdding1NewItemWithNonAdminId_thenThrowsAdminPrivilegeException(){
        //given
        ItemDatabase itemDatabase = new ItemDatabase();
        AdminDatabase adminDatabase = new AdminDatabase();
        ItemService itemService = new ItemService(itemDatabase, adminDatabase);

        //when
        Item newItem = new Item("Milk", "Nice for bones", 50, 50);
        String adminId = "6b84ceb0-69f7-4232-b2c8-e7648ded6baf";

        //then
        assertThrows(AdminPrivilegeException.class, () -> {
            itemService.addNewItem(adminId, newItem);
        });

    }

    @Test
    void givenNewItemService_whenAdding1NewItemWithEmptyName_thenThrowsIllegalArgumentException(){
        //given
        ItemDatabase itemDatabase = new ItemDatabase();
        AdminDatabase adminDatabase = new AdminDatabase();
        ItemService itemService = new ItemService(itemDatabase, adminDatabase);

        //when
        Item newItem = new Item("", "Nice for bones", 50, 50);
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            itemService.addNewItem(adminId, newItem);
        });

    }

    @Test
    void givenNewItemService_whenAdding1NewItemWithEmptyDescription_thenThrowsIllegalArgumentException(){
        //given
        ItemDatabase itemDatabase = new ItemDatabase();
        AdminDatabase adminDatabase = new AdminDatabase();
        ItemService itemService = new ItemService(itemDatabase, adminDatabase);

        //when
        Item newItem = new Item("Bread", "", 50, 50);
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            itemService.addNewItem(adminId, newItem);
        });

    }

    @Test
    void givenNewItemService_whenAdding1NewItemWithNegativePrice_thenThrowsIllegalArgumentException(){
        //given
        ItemDatabase itemDatabase = new ItemDatabase();
        AdminDatabase adminDatabase = new AdminDatabase();
        ItemService itemService = new ItemService(itemDatabase, adminDatabase);

        //when
        Item newItem = new Item("Orange", "Nice for bones", -24, 50);
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            itemService.addNewItem(adminId, newItem);
        });

    }

    @Test
    void givenNewItemService_whenAdding1NewItemWithNegativeAmountInStock_thenThrowsIllegalArgumentException(){
        //given
        ItemDatabase itemDatabase = new ItemDatabase();
        AdminDatabase adminDatabase = new AdminDatabase();
        ItemService itemService = new ItemService(itemDatabase, adminDatabase);

        //when
        Item newItem = new Item("Orange", "Nice for bones", 2, -12);
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            itemService.addNewItem(adminId, newItem);
        });

    }

    @Test
    void givenNewItemService_whenAdding1NewItem_thenNewItemIsInDatabase(){
        //given
        ItemDatabase itemDatabase = new ItemDatabase();
        AdminDatabase adminDatabase = new AdminDatabase();
        ItemService itemService = new ItemService(itemDatabase, adminDatabase);

        //when
        Item newItem = new Item("Orange", "Nice for bones", 2, 12);
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        itemService.addNewItem(adminId, newItem);

        //then
        assertEquals(newItem, itemDatabase.getItemById(newItem.getId()));
    }

}