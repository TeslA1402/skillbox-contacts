package org.example.contact;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactStoreService contactStoreService;

    public ContactService(ContactRepository contactRepository, ContactStoreService contactStoreService) {
        this.contactRepository = contactRepository;
        this.contactStoreService = contactStoreService;
    }

    public void add(String s) {
        Contact contact = Contact.parseContact(s);
        contactRepository.add(contact);
    }

    public List<Contact> getAll() {
        return contactRepository.getAll();
    }

    public Contact removeByEmail(String email) {
        Contact contact = contactRepository.remove(email);
        if (contact == null) {
            throw new RuntimeException("Contact not found");
        }
        return contact;
    }

    public void saveInFile() {
        try {
            contactStoreService.saveInFile();
        } catch (IOException e) {
            throw new RuntimeException("Save file exception", e);
        }
    }
}
