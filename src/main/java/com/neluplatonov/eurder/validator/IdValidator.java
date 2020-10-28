package com.neluplatonov.eurder.validator;

import java.util.UUID;

public class IdValidator {
    public static void validateUUID(String inputId){
        try{
            UUID uuid = UUID.fromString(inputId);
        } catch (Exception ex){
            throw new IllegalArgumentException("The ID you have provided is not valid. Please provide a ID that has the UUID format.");
        }
    }
}
