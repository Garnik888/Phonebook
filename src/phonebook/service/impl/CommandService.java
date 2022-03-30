package phonebook.service.impl;

import phonebook.model.Contact;
import phonebook.model.EmailType;
import phonebook.model.PhoneNumberType;
import phonebook.service.Commands;
import phonebook.service.CommandsEnum;

import java.util.*;

public class CommandService implements Commands {
    private Scanner in = new Scanner(System.in);

    private String name;
    private Contact contact=new Contact();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void create() {

        System.out.println("Input contact's name");
        name = in.next();
        createPhoneNumberMap();
        System.out.print("Input company name or Enter -> ");
        String company = in.next();
        contact.setCompany(company);
        System.out.println("Do you want to add email?(YES/NO");
        String want = in.next();
        if (want.equalsIgnoreCase("yes")) {
            createEmailMap();
        } else {
            contact.getEmails().put(EmailType.EMPTY, "");
        }
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

    public static void printCommands() {

        System.out.println("INPUT " + CommandsEnum.CREATE_CONTACT.ordinal() + " FOR CREATE A NEW CONTACT");
        System.out.println("INPUT " + CommandsEnum.READ_CONTACT.ordinal() + " FOR VIEW ALL CONTACTS");
        System.out.println("INPUT " + CommandsEnum.UPDATE_CONTACT.ordinal() + " FOR UPDATE CONTACTS");
        System.out.println("INPUT " + CommandsEnum.DELETE_CONTACT.ordinal() + " FOR DELETE CONTACTS");
        System.out.println("INPUT " + CommandsEnum.EXIT.ordinal() + " FOR LOGOUT");
    }

    public void printPhoneNumbersType() {
        System.out.println("Choose PHONENUMBER TYPE otherwise press ENTER\n");

        System.out.println("INPUT " + PhoneNumberType.MOBILE.ordinal() + " FOR MOBILE");
        System.out.println("INPUT " + PhoneNumberType.HOME.ordinal() + " FOR HOME");
        System.out.println("INPUT " + PhoneNumberType.WORK.ordinal() + " FOR WORK");
        System.out.println("INPUT " + PhoneNumberType.SCHOOL.ordinal() + " FOR SCHOOL");
        System.out.println("INPUT " + PhoneNumberType.OTHER.ordinal() + " FOR OTHER");
    }

    public void createPhoneNumberMap() {
        System.out.print("Input phone number -> ");
        String phoneNumber = in.next();
        System.out.print("Do you want to add phoneNumber Type?(YES/NO) -> ");
        String want1 = in.next();
        if (want1.equalsIgnoreCase("yes")) {
            printPhoneNumbersType();
            System.out.print("->");
            int typeNumber = in.nextInt();

            switch (typeNumber) {
                case 0:

                    break;
                case 1:
                    contact.getPhoneNumbers().put(PhoneNumberType.HOME, phoneNumber);
                    break;
                case 2:
                    contact.getPhoneNumbers().put(PhoneNumberType.WORK, phoneNumber);
                    break;
                case 3:
                    contact.getPhoneNumbers().put(PhoneNumberType.SCHOOL, phoneNumber);
                    break;
                case 4:
                    contact.getPhoneNumbers().put(PhoneNumberType.OTHER, phoneNumber);
                    break;
                default:
                    System.out.println("Invalid type number.");
                    break;
            }
        } else {
            contact.getPhoneNumbers().put(PhoneNumberType.MOBILE, phoneNumber);
        }
    }

    public void createEmailMap() {
        printEmailType();
        System.out.print("->");
        int typeNUmber = in.nextInt();
        System.out.print("Input email -> ");
        String email = in.next();
        switch (typeNUmber) {
            case 0:
                contact.getEmails().put(EmailType.GMAIL, email);
                break;
            case 1:
                contact.getEmails().put(EmailType.ICLOUD, email);
                break;
            case 2:
                contact.getEmails().put(EmailType.OTHER, email);
                break;
            default:
                System.out.println("Invalid type number.");
                break;
        }
    }

    public void printEmailType() {
        System.out.println("Choose EMAIL TYPE otherwise press ENTER\n");

        System.out.println("INPUT " + EmailType.GMAIL.ordinal() + " FOR GMAIL");
        System.out.println("INPUT " + EmailType.ICLOUD.ordinal() + " FOR ICLOUD");
        System.out.println("INPUT " + EmailType.OTHER.ordinal() + " FOR OTHER");
    }
}
