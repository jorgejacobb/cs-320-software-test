package edu.snhu.contacts;

import java.util.HashMap;
import java.util.Map; 

public class ContactService {

    // simple in-memory store: id -> contact
    private final Map<String, Contact> contacts = new HashMap<>();

    /** Add a contact. Id must be unique. Returns the stored contact. */
    public Contact addContact(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("contact cannot be null");
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate contactId: " + id);
        }
        contacts.put(id, contact);
        return contact;
    }

    /** Delete a contact by id. Throws if the id does not exist. */
    public void deleteContact(String contactId) {
        if (contactId == null) throw new IllegalArgumentException("contactId cannot be null");
        if (contacts.remove(contactId) == null) {
            throw new IllegalArgumentException("No contact with id " + contactId);
        }
    }

    // --- update operations (by id) ---
    public void updateFirstName(String id, String firstName) {
        get(id).setFirstName(firstName);
    }

    public void updateLastName(String id, String lastName) {
        get(id).setLastName(lastName);
    }

    public void updatePhone(String id, String phone) {
        get(id).setPhone(phone);
    }

    public void updateAddress(String id, String address) {
        get(id).setAddress(address);
    }

    // optional helper for tests or UI
    public Contact getContact(String id) {
        return get(id);
    }

    // fetch or fail
    private Contact get(String id) {
        if (id == null) throw new IllegalArgumentException("contactId cannot be null");
        Contact c = contacts.get(id);
        if (c == null) throw new IllegalArgumentException("No contact with id " + id);
        return c;
    }
}