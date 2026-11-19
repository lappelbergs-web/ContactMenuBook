package se.jensen.leo;

import java.util.Scanner;
import se.jensen.leo.models.Contact;
import se.jensen.leo.models.ContactBook;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private ContactBook contactBook = new ContactBook();

    void run() {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addContact();
                case 2 -> listContacts();
                case 3 -> showContact();
                case 4 -> editContact();
                case 5 -> deleteContact();
                case 0 -> running = false;
                default -> IO.println("Unknown choice: " + choice);
            }
        }
        IO.println("Goodbye!");
    }

    void showMenu() {
        IO.println("\n=== Contact book ===");
        IO.println("1. Add contact");
        IO.println("2. Show all contacts");
        IO.println("3. Show one contact");
        IO.println("4. Edit contact");
        IO.println("5. Delete contact");
        IO.println("0. Quit");
        IO.print("Choose: ");
    }

    void addContact() {
        String name = readName();
        IO.print("Email: ");
        String email = scanner.nextLine();
        IO.print("Phone: ");
        String phone = scanner.nextLine();
        contactBook.addContact(new Contact(name, email, phone));
        IO.println("Contact added.");
    }

    void listContacts() {
        if (contactBook.size() == 0) {
            IO.println("No contacts yet.");
        } else {
            for (int i = 0; i < contactBook.size(); i++) {
                IO.println((i + 1) + ". " + contactBook.getContact(i));
            }
        }
    }

    void showContact() {
        Contact contact = chooseContact();
        if (contact != null) {
            IO.println("Name:  " + contact.getName());
            IO.println("Email: " + contact.getEmail());
            IO.println("Phone: " + contact.getPhone());
        }
    }

    void editContact() {
        Contact contact = chooseContact();
        if (contact != null) {
            contact.setName(readName());
            IO.print("New email: ");
            contact.setEmail(scanner.nextLine());
            IO.print("New phone: ");
            contact.setPhone(scanner.nextLine());
            IO.println("Contact updated.");
        }
    }

    void deleteContact() {
        Contact contact = chooseContact();
        if (contact != null) {
            contactBook.removeContact(contact);
            IO.println("Contact deleted.");
        }
    }

    Contact chooseContact() {
        if (contactBook.size() == 0) {
            IO.println("No contacts yet.");
            return null;
        }
        listContacts();
        IO.print("Contact number: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        if (number < 1 || number > contactBook.size()) {
            IO.println("No contact with number " + number);
            return null;
        }
        return contactBook.getContact(number - 1);
    }

    String readName() {
        IO.print("Name: ");
        String name = scanner.nextLine();
        while (name.isBlank()) {
            IO.println("Name cannot be empty.");
            IO.print("Name: ");
            name = scanner.nextLine();
        }
        return name;
    }
}
