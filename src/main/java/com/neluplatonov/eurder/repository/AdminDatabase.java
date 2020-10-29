package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Admin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdminDatabase {
    private Map<String, Admin> admins = new HashMap<>();

    public AdminDatabase() {
        // Populating/Seeding initial database
        Admin initialAdmin = new Admin("Jake", "The Admin", "jake@admin.com");
        initialAdmin.setId("de6def71-53ca-4e5e-85ef-9ed3ab598391");
        admins.put(initialAdmin.getId(), initialAdmin);
    }

    public boolean isUserAnAdmin(String userId){
        return admins.get(userId) != null;
    }
}
