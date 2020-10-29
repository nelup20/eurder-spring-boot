package com.neluplatonov.eurder.validator;

import java.util.List;
import java.util.UUID;

public class IdValidator {
    public static void validateSingleUUID(String inputId){
        try{
            UUID.fromString(inputId);
        } catch (Exception ex){
            throw new IllegalArgumentException("The ID you have provided is not valid. Please provide a ID that has the UUID format.");
        }
    }

    public static void validateListOfUUIDs(List<String> idList){
        try{
            idList.forEach(UUID::fromString);
        } catch (Exception ex){
            throw new IllegalArgumentException("At least one of the provided ID's is not valid. Please make sure that all of the ID's are of the UUID format.");
        }
    }
}
