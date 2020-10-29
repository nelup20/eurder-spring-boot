package com.neluplatonov.eurder.validator;

import java.util.List;

public class ItemValidator {

    public static <T> void validateItemArguments(List<T> argumentsToValidate){
        for(T argument : argumentsToValidate){
            if(argument instanceof String && ((String) argument).length() == 0) throw new IllegalArgumentException("An item's name and/or description can't be empty!");

            if(argument instanceof Integer && ((Integer) argument) < 0) throw new IllegalArgumentException("An item's numeric field can't be negative!");

            if(argument instanceof Double && ((Double) argument) < 0) throw new IllegalArgumentException("An item's numeric field can't be negative!");

        }
    }
}
