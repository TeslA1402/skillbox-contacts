package org.example.printer;

import org.example.contact.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterService {
    public void print(List<Contact> contacts) {
        contacts.forEach(System.out::println);
    }
}
