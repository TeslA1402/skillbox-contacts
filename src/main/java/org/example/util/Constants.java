package org.example.util;

import java.util.regex.Pattern;

public class Constants {
    public static final Pattern CONTACT_PATTERN = Pattern.compile("^([\\p{IsAlphabetic} ]+);(\\+?[1-9]\\d{7,14});(\\S+@\\S+\\.\\S+)$");

    private Constants() {
    }
}
