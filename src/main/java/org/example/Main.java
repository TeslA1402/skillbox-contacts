package org.example;

import org.example.config.AppConfig;
import org.example.contact.Contact;
import org.example.contact.ContactService;
import org.example.printer.PrinterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.MessageFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PrinterService printerService = context.getBean(PrinterService.class);
        ContactService contactService = context.getBean(ContactService.class);

        boolean inProgress = true;
        while (inProgress) {
            try {
                System.out.println(System.lineSeparator() + "Input command(ADD,LIST,DELETE,SAVE,EXIT): ");
                Command command;
                try {
                    command = Command.valueOf(in.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.err.println("Unknown command");
                    continue;
                }
                switch (command) {
                    case ADD -> {
                        System.out.println("Input contact in format(name;number;email): ");
                        contactService.add(in.nextLine());
                    }
                    case LIST -> {
                        System.out.println("List contacts:");
                        printerService.print(contactService.getAll());
                    }
                    case DELETE -> {
                        System.out.println("Input email for remove");
                        String email = in.nextLine();
                        Contact contact = contactService.removeByEmail(email);
                        System.out.println(MessageFormat.format("Contact removed: {0}", contact));
                    }
                    case SAVE -> {
                        contactService.saveInFile();
                        System.out.println("Complete save contacts in file");
                    }
                    case EXIT -> inProgress = false;
                    default -> System.err.print("Unsupported command");
                }

            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}