package phonebook.service.impl;

import phonebook.model.Contact;
import phonebook.model.EmailType;
import phonebook.model.PhoneNumberType;
import phonebook.service.Commands;
import phonebook.service.CommandsEnum;
import phonebook.service.UpdateCommandsEnum;

import javax.swing.*;
import java.util.*;

public class CommandService implements Commands {
    private Scanner in = new Scanner(System.in);

    private String name;
    private Contact contact = new Contact();

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
    public void create(Map<String, Contact> map) {

        System.out.print("\u001B[34m" + "Input contact's name -> ");
        String name = in.next();

        createPhoneNumberSet();

        while (true) {

            System.out.print("\u001B[34m" + "Do you want create another phone number? (Y/N) -> ");
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                createPhoneNumberSet();
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

        createEmailSet();

        System.out.print("\u001B[34m" + "Do you want create company name? (Y/N) -> ");

        while (true) {
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("\u001B[34m" + "Input company name  -> ");
                String company = in.next();

                contact.setCompany(company);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

        map.put(name, contact);
    }

    /**
     * Map phone number editor
     */
    private void createPhoneNumberSet() {

        Set<String> phoneNumberSet = new HashSet<>();
        System.out.print("\u001B[34m" + "Input phone number -> ");
        String phoneNumber = in.next();

        phoneNumberSet.add(phoneNumber);

        System.out.print("\u001B[34m" + "Do you want to add phone number Type?(Y/N) -> ");

        while (true) {

            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                printPhoneNumbersType();
                System.out.print(" \n-> ");
                int typeNumber = in.nextInt();

                switch (typeNumber) {
                    case 0:
                        phoneNumberMapBuilder(PhoneNumberType.MOBILE, phoneNumberSet, phoneNumber);
                        break;
                    case 1:
                        phoneNumberMapBuilder(PhoneNumberType.HOME, phoneNumberSet, phoneNumber);
                        break;
                    case 2:
                        phoneNumberMapBuilder(PhoneNumberType.WORK, phoneNumberSet, phoneNumber);
                        break;
                    case 3:
                        phoneNumberMapBuilder(PhoneNumberType.SCHOOL, phoneNumberSet, phoneNumber);
                        break;
                    case 4:
                        phoneNumberMapBuilder(PhoneNumberType.OTHER, phoneNumberSet, phoneNumber);
                        break;
                    default:
                        System.out.println("\u001B[31m" + "Invalid type number.");
                        break;
                }

                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.print("\u001B[31m" + "Wrong choose, input Y/N -> ");
            } else {

                phoneNumberMapBuilder(PhoneNumberType.OTHER, phoneNumberSet, phoneNumber);
                break;
            }
        }
    }

    /**
     * Build phone number
     *
     * @param type           PhoneNumberType Enum type
     * @param phoneNumberSet Set<String> type
     * @param phoneNumber    String type
     */
    private void phoneNumberMapBuilder(PhoneNumberType type, Set<String> phoneNumberSet, String phoneNumber) {

        if (!contact.getPhoneNumbers().containsKey(type)) {
            contact.getPhoneNumbers().put(type, phoneNumberSet);
        } else {

            contact.getPhoneNumbers().get(type).add(phoneNumber);
        }
    }

    /**
     * Print phone type number list
     */
    private void printPhoneNumbersType() {
        System.out.println("\"\\u001B[34m\"+Choose phone number TYPE otherwise input 9\n");

        System.out.println("\u001B[32m" + "INPUT " + PhoneNumberType.MOBILE.ordinal() + " FOR MOBILE");
        System.out.println("INPUT " + PhoneNumberType.HOME.ordinal() + " FOR HOME");
        System.out.println("INPUT " + PhoneNumberType.WORK.ordinal() + " FOR WORK");
        System.out.println("INPUT " + PhoneNumberType.SCHOOL.ordinal() + " FOR SCHOOL");
        System.out.print("INPUT " + PhoneNumberType.OTHER.ordinal() + " FOR OTHER" + "\u001B[0m");
    }

    /**
     * Map email editor
     */
    private void createEmailSet() {

        System.out.print("\u001B[34m" + "Do you want to add email?(Y/N) -> ");
        Set<String> emailSet = new HashSet<>();

        while (true) {
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("\u001B[34m" + "Input email -> ");
                String email = in.next();

                emailSet.add(email);

                printEmailType();
                System.out.print("->");
                int typeNumber = in.nextInt();

                switch (typeNumber) {
                    case 0:
                        emailMapBuilder(EmailType.GMAIL, emailSet, email);
                        break;
                    case 1:
                        emailMapBuilder(EmailType.ICLOUD, emailSet, email);
                        break;
                    case 2:
                        emailMapBuilder(EmailType.OTHER, emailSet, email);
                        break;
                    default:
                        System.out.println("\u001B[31m" + "Invalid type number.");
                        break;
                }
                break;

            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                emailMapBuilder(EmailType.OTHER, emailSet, "--------");
                break;
            }
        }
    }

    /**
     * Build email
     *
     * @param type     EmailType Enum type
     * @param emailSet Set<String> type
     * @param email    String type
     */
    private void emailMapBuilder(EmailType type, Set<String> emailSet,
                                 String email) {
        if (!contact.getEmails().containsKey(type)) {
            contact.getEmails().put(type, emailSet);
        } else {

            contact.getEmails().get(type).add(email);
        }

    }

    /**
     * Print email type list
     */
    private void printEmailType() {
        System.out.println("\"\\u001B[34m\"Choose EMAIL TYPE otherwise press ENTER\n");

        System.out.println("INPUT " + EmailType.GMAIL.ordinal() + " FOR GMAIL");
        System.out.println("INPUT " + EmailType.ICLOUD.ordinal() + " FOR ICLOUD");
        System.out.println("INPUT " + EmailType.OTHER.ordinal() + " FOR OTHER");
    }

    @Override
    public void getContact(Map<String, Contact> map) {
        Map<String, Contact> map1 = new TreeMap<>(map);

        for (Map.Entry<String, Contact> contactName : map1.entrySet()) {
            System.out.println(contactName.getKey());
        }
        System.out.print("\u001B[34m" + "Input name -> ");
        String name = in.next();

        while (!map.containsKey(name)) {
            System.out.print("\u001B[31m" + "Wrong name: input right name ->");
            name = in.next();
        }

        System.out.println("\u001B[33m" + "------ CONTACTS------\n" + "\n---" + name + "---\n"
                + "Phone number(s) ->" + contact.getPhoneNumbers() + "\nCompany ->"
                + contact.getCompany() + "\nEmail(s) ->" + contact.getEmails());
    }

    @Override
    public void update(Map<String, Contact> map) {

        Map<String, Contact> map1 = new TreeMap<>(map);

        for (Map.Entry<String, Contact> contactName : map1.entrySet()) {
            System.out.println(contactName.getKey());
        }

        System.out.print("\u001B[34m" + "Input Contact name which you want to update: -> ");
        String name = in.next();

        while (!map.containsKey(name)) {
            System.out.println("\u001B[31m" + "Not found contact by this name. Try again! -> ");
            name = in.next();
        }

        System.out.println(name);
        System.out.println(map.get(name));

        printUpdateType();

        System.out.println("\u001B[34m" + "Choose number of operation which do you want to update?");
        int choice = in.nextInt();


        switch (choice) {
            case 0:
                System.out.println("\u001B[34m" + "Input contact's new name. ->");
                String newName = in.next();
                map.put(newName, map.get(name));
                map.remove(name);
                System.out.println("\u001B[36m" + "The contact's name is updated.");
                break;
            case 1:
                phoneNumberUpdate(name, map);
                System.out.println("\u001B[36m" + "The contact's phone number is updated.");
                break;
            case 2:
                System.out.println("\u001B[36m" + "If tou want delete ALL phone typ input 0");
                System.out.println("\u001B[36m" + "If tou want delete phone number input 1");

                while (true) {

                    int del = in.nextInt();

                    if (del == 1) {

                        printPhoneNumbersType();


                        while (true) {
                            System.out.print(" \n-> ");
                            int typeNumber = in.nextInt();

                            switch (typeNumber) {
                                case 0:
                                    if (!contact.getPhoneNumbers().containsKey(PhoneNumberType.MOBILE)) {
                                        contact.getPhoneNumbers().remove(PhoneNumberType.MOBILE);
                                    }
                                    break;
                                case 1:
                                    if (!contact.getPhoneNumbers().containsKey(PhoneNumberType.HOME)) {
                                        contact.getPhoneNumbers().remove(PhoneNumberType.HOME);
                                    }
                                    break;
                                case 2:
                                    if (!contact.getPhoneNumbers().containsKey(PhoneNumberType.WORK)) {
                                        contact.getPhoneNumbers().remove(PhoneNumberType.WORK);
                                    }
                                    break;
                                case 3:
                                    if (!contact.getPhoneNumbers().containsKey(PhoneNumberType.SCHOOL)) {
                                        contact.getPhoneNumbers().remove(PhoneNumberType.SCHOOL);
                                    }
                                    break;
                                case 4:
                                    if (!contact.getPhoneNumbers().containsKey(PhoneNumberType.OTHER)) {
                                        contact.getPhoneNumbers().remove(PhoneNumberType.OTHER);
                                    }
                                    break;
                                default:
                                    System.out.println("\u001B[31m" + "Invalid type number.");
                                    continue;
                            }
                            break;
                        }

                    } else if (del == 2) {

                        System.out.println(contact.getPhoneNumbers());
                        System.out.print("Choose phone numbers type");
                        printPhoneNumbersType();

                        while (true) {
                            System.out.print(" \n-> ");
                            int typeNumber = in.nextInt();

                            switch (typeNumber) {
                                case 0:
                                    while (true) {

                                        System.out.print("Input phone numbers -> ");
                                        String number = in.next();
                                        if (!contact.getPhoneNumbers().get(PhoneNumberType.MOBILE).contains(number)) {

                                            contact.getPhoneNumbers().get(PhoneNumberType.MOBILE).remove(number);

                                            break;
                                        } else {

                                            System.out.println("Incorrect number");
                                        }

                                    }
                                    break;
                                case 1:
                                    while (true) {

                                        System.out.print("Input phone numbers -> ");
                                        String number = in.next();
                                        if (!contact.getPhoneNumbers().get(PhoneNumberType.HOME).contains(number)) {

                                            contact.getPhoneNumbers().get(PhoneNumberType.HOME).remove(number);

                                            break;
                                        } else {

                                            System.out.println("Incorrect number");
                                        }

                                    }
                                    break;
                                case 2:
                                    while (true) {

                                        System.out.print("Input phone numbers -> ");
                                        String number = in.next();
                                        if (!contact.getPhoneNumbers().get(PhoneNumberType.WORK).contains(number)) {

                                            contact.getPhoneNumbers().get(PhoneNumberType.WORK).remove(number);

                                            break;
                                        } else {

                                            System.out.println("Incorrect number");
                                        }

                                    }
                                    break;
                                case 3:
                                    while (true) {

                                        System.out.print("Input phone numbers -> ");
                                        String number = in.next();
                                        if (!contact.getPhoneNumbers().get(PhoneNumberType.SCHOOL).contains(number)) {

                                            contact.getPhoneNumbers().get(PhoneNumberType.SCHOOL).remove(number);

                                            break;
                                        } else {

                                            System.out.println("Incorrect number");
                                        }

                                    }
                                    break;
                                case 4:
                                    while (true) {

                                        System.out.print("Input phone numbers -> ");
                                        String number = in.next();
                                        if (!contact.getPhoneNumbers().get(PhoneNumberType.OTHER).contains(number)) {

                                            contact.getPhoneNumbers().get(PhoneNumberType.OTHER).remove(number);

                                            break;
                                        } else {

                                            System.out.println("Incorrect number");
                                        }

                                    }
                                    break;
                                default:
                                    System.out.println("\u001B[31m" + "Invalid type number.");
                                    continue;
                            }

                            break;
                        }

                        break;
                    }
                }
            case 3:
                emailUpdate(name, map);
                System.out.println("\u001B[36m" + "The contact's email is updated.");
                break;
            case 4:
                System.out.println("\u001B[36m" + "If tou want delete ALL email typ input 0");
                System.out.println("\u001B[36m" + "If tou want delete email input 1");

                while (true) {

                    int del = in.nextInt();

                    if (del == 1) {

                        printEmailType();

                        while (true) {
                            System.out.print(" \n-> ");
                            int typeEmail = in.nextInt();

                            switch (typeEmail) {
                                case 0:
                                    if (!contact.getEmails().containsKey(EmailType.GMAIL)) {
                                        contact.getEmails().remove(EmailType.GMAIL);
                                    }
                                    break;
                                case 1:
                                    if (!contact.getEmails().containsKey(EmailType.ICLOUD)) {
                                        contact.getEmails().remove(EmailType.ICLOUD);
                                    }
                                    break;
                                case 2:
                                    if (!contact.getEmails().containsKey(EmailType.OTHER)) {
                                        contact.getEmails().remove(EmailType.OTHER);
                                    }
                                    break;
                                default:
                                    System.out.println("\u001B[31m" + "Invalid type number.");
                                    continue;
                            }
                            break;
                        }

                    } else if (del == 2) {

                        System.out.println(contact.getEmails());
                        System.out.print("Choose email type");
                        printEmailType();

                        while (true) {
                            System.out.print(" \n-> ");
                            int typeNumber = in.nextInt();

                            switch (typeNumber) {
                                case 0:
                                    while (true) {

                                        System.out.print("Input email -> ");
                                        String email = in.next();
                                        if (!contact.getEmails().get(EmailType.GMAIL).contains(email)) {

                                            contact.getEmails().get(EmailType.GMAIL).remove(email);

                                            break;
                                        } else {

                                            System.out.println("Incorrect number");
                                        }

                                    }
                                    break;
                                case 1:
                                    while (true) {

                                        System.out.print("Input email -> ");
                                        String email = in.next();
                                        if (!contact.getEmails().get(EmailType.ICLOUD).contains(email)) {

                                            contact.getEmails().get(EmailType.ICLOUD).remove(email);

                                            break;
                                        } else {

                                            System.out.println("Incorrect number");
                                        }

                                    }
                                    break;
                                case 2:
                                    while (true) {

                                        System.out.print("Input email -> ");
                                        String email = in.next();
                                        if (!contact.getEmails().get(EmailType.OTHER).contains(email)) {

                                            contact.getEmails().get(EmailType.OTHER).remove(email);

                                            break;
                                        } else {

                                            System.out.println("Incorrect number");
                                        }

                                    }
                                    break;

                                default:
                                    System.out.println("\u001B[31m" + "Invalid type number.");
                                    continue;
                            }

                            break;
                        }
                        break;
                    }
                }

                break;
            case 5:
                System.out.println("\u001B[34m" + "Input company's new name. ->");
                String newCompany = in.next();
                contact.setCompany(newCompany);
                System.out.println("\u001B[36m" + "The contact's company name is updated.");
                break;
            case 6:
                System.out.println("\u001B[34m" + "Input company's new name. ->");
                String company = in.next();
                contact.setCompany(null);
                break;
            default:
                System.out.println("\u001B[31m" + "Invalid choice. Input right number!");
                break;
        }
    }

    /**
     * @param name
     * @param map
     */
    private void phoneNumberUpdate(String name, Map<String, Contact> map) {

        System.out.println("\u001B[34m" + "Choose phone number type.");
        printPhoneNumbersType();
        System.out.print(" \n-> ");
        int typeNumber = in.nextInt();

        switch (typeNumber) {
            case 0:
                phoneNumberUpdateCase(name, PhoneNumberType.MOBILE, map);
                break;
            case 1:
                phoneNumberUpdateCase(name, PhoneNumberType.HOME, map);
                break;
            case 2:
                phoneNumberUpdateCase(name, PhoneNumberType.WORK, map);
                break;
            case 3:
                phoneNumberUpdateCase(name, PhoneNumberType.SCHOOL, map);
                break;
            case 4:
                phoneNumberUpdateCase(name, PhoneNumberType.OTHER, map);
                break;
            default:
                System.out.println("\u001B[31m" + "Invalid type number.");
                break;
        }
    }

    private void phoneNumberUpdateCase(String name, PhoneNumberType type, Map<String, Contact> map) {

        System.out.println(map.get(name).getPhoneNumbers().get(type));
        System.out.print("\u001B[34m" + "Input phone number which one do you want to update. -> ");
        String phoneNumber = in.next();
        while (!contact.getPhoneNumbers().get(type).contains(phoneNumber)) {
            System.out.print("\u001B[34m" + "Input right phone number! -> ");
            phoneNumber = in.next();
        }
        System.out.print("\u001B[34m" + "Input new phone number. -> ");
        String newPhoneNumber = in.next();
        map.get(name).getPhoneNumbers().get(type).remove(phoneNumber);
        map.get(name).getPhoneNumbers().get(type).add(newPhoneNumber);
    }

    /**
     * Update email
     *
     * @param name String type
     * @param map  Map<String, Contact> map) type
     */
    private void emailUpdate(String name, Map<String, Contact> map) {

        System.out.println("\u001B[34m" + "Choose email type.");
        printEmailType();
        System.out.print(" \n-> ");
        int typeNumber = in.nextInt();

        switch (typeNumber) {
            case 0:
                emailUpdateCase(name, EmailType.GMAIL, map);
                break;
            case 1:
                emailUpdateCase(name, EmailType.ICLOUD, map);
                break;
            case 2:
                emailUpdateCase(name, EmailType.OTHER, map);
                break;
            default:
                System.out.println("\u001B[31m" + "Invalid type number.");
                break;
        }
    }

    /**
     * Email update case
     *
     * @param type EmailType type
     * @param map  Map<String, Contact> type
     */
    private void emailUpdateCase(String name, EmailType type, Map<String, Contact> map) {
        System.out.println(map.get(name).getEmails().get(type));
        System.out.print("\u001B[34m" + "Input email which one do you want to update. -> ");
        String email = in.next();

        while (!contact.getEmails().get(type).contains(email)) {
            System.out.print("\u001B[34m" + "Input right email! -> ");
            email = in.next();
        }
        System.out.print("\u001B[34m" + "Input new email. -> ");
        String newEmail = in.next();
        map.get(name).getEmails().get(type).remove(email);
        map.get(name).getEmails().get(type).add(newEmail);
    }

    /**
     * Print update type list
     */
    private void printUpdateType() {
        System.out.println("\u001B[34m" + "\nChoose update TYPE: \n");

        System.out.println("\u001B[32m" + "INPUT " + UpdateCommandsEnum.NAME_UPDATE.ordinal() + " FOR update Name");
        System.out.println("INPUT " + UpdateCommandsEnum.PHONE_NUMBER_UPDATE.ordinal() + " FOR update PhoneNumber");
        System.out.println("INPUT " + UpdateCommandsEnum.PHONE_NUMBER_UPDATE.ordinal() + " FOR delete PhoneNumber");
        System.out.println("INPUT " + UpdateCommandsEnum.EMAIL_UPDATE.ordinal() + " FOR update Email");
        System.out.println("INPUT " + UpdateCommandsEnum.EMAIL_UPDATE.ordinal() + " FOR delete Email");
        System.out.println("INPUT " + UpdateCommandsEnum.COMPANY_NAME_UPDATE.ordinal() +
                " FOR update company name" + "\u001B[0m");
        System.out.println("INPUT " + UpdateCommandsEnum.COMPANY_NAME_UPDATE.ordinal() +
                " FOR delete company name" + "\u001B[0m");
    }

    /**
     * Delete contact
     *
     * @param map HashMap<> type
     */
    @Override
    public void delete(Map<String, Contact> map) {

        while (true) {

            System.out.print("\u001B[34m" + "Input contact's name which you want to delete -> ");
            String name = in.next();

            while (map.get(name) == null) {

                System.out.print("Contact by this name is not found!" + "\nInput right name -> ");

                name = in.next();
            }

            System.out.println("\u001B[34m" + "Are you sure (Y/N) -> ");
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                map.remove(name);
                System.out.print("\u001B[36m" + "The contact is deleted.");
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }
    }

    public static void printCommands() {

        System.out.println("\u001B[32m" + "INPUT " + CommandsEnum.CREATE_CONTACT.ordinal() + " FOR CREATE A NEW CONTACT");
        System.out.println("INPUT " + CommandsEnum.READ_CONTACT.ordinal() + " FOR VIEW ALL CONTACTS");
        System.out.println("INPUT " + CommandsEnum.UPDATE_CONTACT.ordinal() + " FOR UPDATE CONTACTS");
        System.out.println("INPUT " + CommandsEnum.DELETE_CONTACT.ordinal() + " FOR DELETE CONTACTS");
        System.out.println("INPUT " + CommandsEnum.EXIT.ordinal() + " FOR LOGOUT" + "\u001B[0m");
    }

    @Override
    public String toString() {
        return "\u001B[35m" + "CommandService{" + "name='" + name + '\'' + ", contact=" + contact + '}';
    }
}
