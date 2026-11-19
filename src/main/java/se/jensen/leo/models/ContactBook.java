package se.jensen.leo.models;

import java.util.ArrayList;

public class ContactBook {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public int size() {
        return contacts.size();
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }
}
