package org.example.contact;

import org.example.util.Constants;

import java.text.MessageFormat;
import java.util.regex.Matcher;

public record Contact(String name, String phone, String email) {

    public static Contact parseContact(String value) {
        Matcher matcher = Constants.CONTACT_PATTERN
                .matcher(value);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Incorrect input. Example: Иванов Иван Иванович;+890999999;someEmail@example.example");
        }
        return new Contact(matcher.group(1).trim(), matcher.group(2).trim(), matcher.group(3).trim());
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} | {1} | {2}", name, phone, email);
    }
}
