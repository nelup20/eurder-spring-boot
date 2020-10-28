package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Admin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdminDatabase {
    private Map<String, Admin> admins = new HashMap<>();

    public AdminDatabase() {
        // Creating 1 initial admin for testing purposes etc.
        Admin newAdmin = new Admin("Jake", "The Admin", "jake@admin.com");
        newAdmin.setId("de6def71-53ca-4e5e-85ef-9ed3ab598391");
        admins.put(newAdmin.getId(), newAdmin);
    }

    public boolean isUserAnAdmin(String userId){
        return admins.get(userId) != null;
    }
}
