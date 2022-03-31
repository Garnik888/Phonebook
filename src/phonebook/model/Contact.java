package phonebook.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Contact {

    //Class fields
    private Map<PhoneNumberType, Map<Integer, String>> phoneNumbers = new HashMap<>();
    private String company = "";
    private Map<EmailType, Map<Integer, String>> emails = new HashMap<>();

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
    public Contact(Map<PhoneNumberType, Map<Integer, String>> phoneNumbers, String company,
                   Map<EmailType, Map<Integer, String>> emails) {
        this.phoneNumbers = phoneNumbers;
        this.company = company;
        this.emails = emails;
    }

    public Map<PhoneNumberType, Map<Integer, String>> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map<PhoneNumberType, Map<Integer, String>> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Map<EmailType, Map<Integer, String>> getEmails() {
        return emails;
    }

    public void setEmails(Map<EmailType, Map<Integer, String>> emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phoneNumbers, contact.phoneNumbers) && Objects.equals(company, contact.company)
                && Objects.equals(emails, contact.emails);
    }
    @Override
    public int hashCode() {
        return Objects.hash(phoneNumbers, company, emails);
    }

    @Override
    public String toString() {
        return "Contacts info : " +
                "Phone numbers : " + phoneNumbers.keySet() + " - " + phoneNumbers.entrySet() +
                "\nCompany name : " + company +
                "\nEmails : " + emails.keySet() + " - " + emails.values();
    }
}
