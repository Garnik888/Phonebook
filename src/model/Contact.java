package model;

import model.enums.EmailType;
import model.enums.PhoneNumberType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Contact implements Serializable {

    //Class fields
    public Map<PhoneNumberType, Set<String>> phoneNumbers = new HashMap<>();
    private String company;
    public Map<EmailType, Set<String>> emails = new HashMap<>();

    /**
     * No argument constructor
     */
    public Contact() {

    }

    /**
     * All arguments constructor
     *
     * @param phoneNumbers
     * @param company
     * @param emails
     */
    public Contact(Map<PhoneNumberType, Set<String>> phoneNumbers, String company,
                   Map<EmailType, Set<String>> emails) {
        this.phoneNumbers = phoneNumbers;
        this.company = company;
        this.emails = emails;
    }

    public Map<PhoneNumberType, Set<String>> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map<PhoneNumberType, Set<String>> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Map<EmailType, Set<String>> getEmails() {
        return emails;
    }

    public void setEmails(Map<EmailType, Set<String>> emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getPhoneNumbers(), contact.getPhoneNumbers()) && Objects.equals(getCompany(), contact.getCompany()) && Objects.equals(getEmails(), contact.getEmails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumbers(), getCompany(), getEmails());
    }

    @Override
    public String toString() {
        return  phoneNumbers  + company  + emails ;
    }
}
