package phonebook.service;

import phonebook.model.EmailType;
import phonebook.model.PhoneNumberType;

import java.util.*;

public class ContactService implements Commands {

    private HashMap<PhoneNumberType, String> phoneNumbers;
    private HashMap<EmailType, String> emails;
    private Scanner in = new Scanner(System.in);

    public static void printCommands() {

        System.out.println("PLEASE INPUT " + CommandsEnum.CREATE_CONTACT.ordinal() + " FOR CREATE A NEW CONTACT");
        System.out.println("PLEASE INPUT " + CommandsEnum.READ_CONTACT.ordinal() + " FOR VIEW ALL CONTACTS");
        System.out.println("PLEASE INPUT " + CommandsEnum.UPDATE_CONTACT.ordinal() + " FOR UPDATE CONTACTS");
        System.out.println("PLEASE INPUT " + CommandsEnum.DELETE_CONTACT.ordinal() + " FOR DELETE CONTACTS");
        System.out.println("PLEASE INPUT " + CommandsEnum.EXIT.ordinal() + " for LOGOUT");
    }

    @Override
    public void create() {

    }

    @Override
    public void getContact() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
