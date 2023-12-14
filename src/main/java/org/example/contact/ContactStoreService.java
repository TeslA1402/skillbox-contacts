package org.example.contact;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactStoreService {
    private final ContactRepository contactRepository;
    @Value("${path.file.save}")
    private String pathToSave;

    @Value("${path.file.init}")
    private String pathToInit;

    public ContactStoreService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    static List<String> getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) throw new IOException();
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().toList();
            }
        }
    }

    public void saveInFile() throws IOException {
        List<Contact> contacts = contactRepository.getAll();
        String stringToSave = contacts.stream()
                .map(contact -> MessageFormat.format("{0};{1};{2}", contact.name(), contact.phone(), contact.email()))
                .collect(Collectors.joining(System.lineSeparator()));
        Files.writeString(Path.of(pathToSave), stringToSave);
    }

    public void initFromFile() throws IOException {
        List<String> strings = pathToInit.isBlank() ?
                getResourceFileAsString("default-contacts.txt") :
                Files.readAllLines(Path.of(pathToInit));
        strings.stream().map(Contact::parseContact).forEach(contactRepository::add);
    }
}
