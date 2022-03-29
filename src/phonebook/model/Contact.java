package phonebook.model;

import java.util.Objects;

public class Contact {

    //Class fields
    private String phoneNumbers;
    private PhoneNumberType phoneNumberType;
    private String company;
    private String emails;
    private EmailType emailType;

    /**
     * No argument constructor
     */
    public Contact() {

    }

    /**
     * All arguments constructor
     * @param phoneNumbers
     * @param phoneNumberType
     * @param company
     * @param emails
     * @param emailType
     */
    public Contact(String phoneNumbers, PhoneNumberType phoneNumberType, String company,
                   String emails, EmailType emailType) {
        this.phoneNumbers = phoneNumbers;
        this.phoneNumberType = phoneNumberType;
        this.company = company;
        this.emails = emails;
        this.emailType = emailType;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setPhoneNumberType(PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getPhoneNumbers(), contact.getPhoneNumbers()) && getPhoneNumberType() == contact.getPhoneNumberType() && Objects.equals(getCompany(), contact.getCompany()) && Objects.equals(getEmails(), contact.getEmails()) && getEmailType() == contact.getEmailType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumbers(), getPhoneNumberType(), getCompany(), getEmails(), getEmailType());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumbers='" + phoneNumbers + '\'' +
                ", phoneNumberType=" + phoneNumberType +
                ", company='" + company + '\'' +
                ", emails='" + emails + '\'' +
                ", emailType=" + emailType +
                '}';
    }
}
