package edu.snhu.contacts;

public class Contact {
    private final String contactId;   // not updatable
    private String firstName;
    private String lastName;
    private String phone;             // exactly 10 digits
    private String address;

    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {
        this.contactId = validateId(contactId);
        this.firstName = validateName(firstName, "firstName");
        this.lastName  = validateName(lastName,  "lastName");
        this.phone     = validatePhone(phone);
        this.address   = validateAddress(address);
    }

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getPhone()     { return phone; }
    public String getAddress()   { return address; }

    // no setter for contactId
    public void setFirstName(String firstName) { this.firstName = validateName(firstName, "firstName"); }
    public void setLastName(String lastName)   { this.lastName  = validateName(lastName,  "lastName"); }
    public void setPhone(String phone)         { this.phone     = validatePhone(phone); }
    public void setAddress(String address)     { this.address   = validateAddress(address); }

    private static String require(String v, String field) {
        if (v == null) throw new IllegalArgumentException(field + " cannot be null");
        return v;
    }
    private static String validateId(String id) {
        id = require(id, "contactId");
        if (id.length() > 10) throw new IllegalArgumentException("contactId must be ≤ 10 chars");
        return id;
    }
    private static String validateName(String name, String field) {
        name = require(name, field);
        if (name.length() > 10) throw new IllegalArgumentException(field + " must be ≤ 10 chars");
        return name;
    }
    private static String validatePhone(String phone) {
        phone = require(phone, "phone");
        if (!phone.matches("\\d{10}")) throw new IllegalArgumentException("phone must be exactly 10 digits");
        return phone;
    }
    private static String validateAddress(String address) {
        address = require(address, "address");
        if (address.length() > 30) throw new IllegalArgumentException("address must be ≤ 30 chars");
        return address;
    }

}
