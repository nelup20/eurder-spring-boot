package com.neluplatonov.eurder.validator;

import java.util.List;

public class ItemValidator {

    public static void validateStringArgumentsAreNotEmpty(List<String> argumentsToValidate){
        if(argumentsToValidate.stream().anyMatch(argument -> argument.length() == 0)) throw new IllegalArgumentException("An item's name and/or description can't be empty!");
    }

    public static void validateNumericArgumentIsNotNegative(int argumentToValidate){
        if(argumentToValidate < 0) throw new IllegalArgumentException("An item's numeric field can't be negative!");
    }

    public static void validateNumericArgumentIsNotNegative(double argumentToValidate){
        if(argumentToValidate < 0) throw new IllegalArgumentException("An item's numeric field can't be negative!");
    }
}
