package edu.snhu.contacts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void createsValidContact() {
        Contact c = new Contact("A12", "Jorge", "Jacob", "1234567890", "123 Palm Ave");
        assertEquals("A12", c.getContactId());
        assertEquals("Jorge", c.getFirstName());
        assertEquals("Jacob", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Palm Ave", c.getAddress());
    }

    // ---- null checks ----
    @Test void idCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact(null, "A", "B", "1234567890", "addr"));
    }
    @Test void firstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", null, "B", "1234567890", "addr"));
    }
    @Test void lastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "A", null, "1234567890", "addr"));
    }
    @Test void phoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "A", "B", null, "addr"));
    }
    @Test void addressCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "A", "B", "1234567890", null));
    }

    // ---- length rules ----
    @Test void idMax10Chars() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ABCDEFGHIJK", "A", "B", "1234567890", "addr")); // 11 chars
    }
    @Test void firstNameMax10Chars() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "01234567890", "B", "1234567890", "addr"));
    }
    @Test void lastNameMax10Chars() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "A", "01234567890", "1234567890", "addr"));
    }
    @Test void addressMax30Chars() {
        String longAddr = "1234567890123456789012345678901"; // 31
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "A", "B", "1234567890", longAddr));
    }

    // ---- phone must be exactly 10 digits ----
    @Test void phoneMustBeExactly10Digits() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "A", "B", "12345", "addr"));          // too short
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "A", "B", "12345678901", "addr"));    // too long
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID1", "A", "B", "12345abcde", "addr"));     // not all digits
    }

    // ---- setters validate too ----
    @Test
    void settersEnforceRules() {
        Contact c = new Contact("ID9", "A", "B", "1111111111", "addr");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("01234567890"));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("01234567890"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("abc"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("01234567890123456789012345678901"));
    }

    // ---- contactId is not updatable ----
    @Test
    void contactIdStaysTheSame() {
        Contact c = new Contact("LOCKED", "A", "B", "1111111111", "addr");
        // there is no setter for id; just confirm existing value remains
        c.setFirstName("Z"); // change other fields
        assertEquals("LOCKED", c.getContactId());
    }
}
