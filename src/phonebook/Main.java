package phonebook;

import phonebook.model.Contact;
import phonebook.model.PhoneNumberType;
import phonebook.service.ContactService;
import phonebook.service.ContactStart;

public class Main {

    public static void main(String[] args) {

//        ContactStart contactStart = new ContactStart();
//        contactStart.start();

        Contact contact = new Contact();
        System.out.println(contact.getPhoneNumberType());
    }
}
