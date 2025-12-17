package edu.snhu.contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setup() { service = new ContactService(); }

    @Test
    void addContact_requiresUniqueId() {
        Contact c1 = new Contact("ID1", "A", "B", "1111111111", "addr");
        Contact c2 = new Contact("ID1", "C", "D", "2222222222", "addr2");
        service.addContact(c1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(c2));
    }

    @Test
    void deleteContact_removesAndThenFailsIfMissing() {
        Contact c = new Contact("ID2", "A", "B", "1111111111", "addr");
        service.addContact(c);
        service.deleteContact("ID2");
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("ID2"));
    }

    @Test
    void updateFieldsById_persistsChanges() {
        Contact c = new Contact("ID3", "A", "B", "1111111111", "addr");
        service.addContact(c);

        service.updateFirstName("ID3", "Jo");
        service.updateLastName("ID3", "Li");
        service.updatePhone("ID3", "1234567890");
        service.updateAddress("ID3", "123 Main St");

        Contact got = service.getContact("ID3");
        assertEquals("Jo", got.getFirstName());
        assertEquals("Li", got.getLastName());
        assertEquals("1234567890", got.getPhone());
        assertEquals("123 Main St", got.getAddress());
    }

    @Test
    void getContact_throwsIfNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.getContact("NOPE"));
    }
}
