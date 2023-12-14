package org.example.contact;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public class ContactRepository {
    private final LinkedHashMap<String, Contact> contacts = new LinkedHashMap<>();

    public List<Contact> getAll() {
        return contacts.values().stream().toList();
    }

    public void add(Contact contact) {
        contacts.put(contact.email(), contact);
    }

    public Contact remove(String email) {
        return contacts.remove(email);
    }
}
