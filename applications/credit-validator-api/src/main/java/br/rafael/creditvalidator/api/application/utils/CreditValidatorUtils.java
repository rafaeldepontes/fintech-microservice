package br.rafael.creditvalidator.api.application.utils;

import java.util.Collection;

public class CreditValidatorUtils {
    
    private CreditValidatorUtils() {}

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
