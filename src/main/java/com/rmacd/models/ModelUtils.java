package com.rmacd.models;

public class ModelUtils {

    public static String toEnum(String input) {
        return input.toUpperCase()
                .replaceAll("[^A-Z]", "_")
                .replaceAll("_+", "_")
                .replaceAll("^_+", "")
                .replaceAll("_+$", "");
    }

}
