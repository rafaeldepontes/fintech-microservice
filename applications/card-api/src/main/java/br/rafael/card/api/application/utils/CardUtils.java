package br.rafael.card.api.application.utils;

import java.util.Collection;

public class CardUtils {
    
    private CardUtils() {}

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
