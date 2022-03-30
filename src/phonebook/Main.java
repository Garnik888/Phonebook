package phonebook;

import phonebook.model.Contact;
import phonebook.model.PhoneNumberType;
import phonebook.service.impl.ContactStart;

public class Main {

    public static void main(String[] args) {

        ContactStart contactStart = new ContactStart();
        contactStart.start();

    }
}
