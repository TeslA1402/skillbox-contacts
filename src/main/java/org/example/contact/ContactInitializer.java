package org.example.contact;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class ContactInitializer {
    private final ContactStoreService contactStoreService;


    public ContactInitializer(ContactStoreService contactStoreService) {
        this.contactStoreService = contactStoreService;
    }

    @PostConstruct
    public void init() throws IOException {
        contactStoreService.initFromFile();
    }
}
