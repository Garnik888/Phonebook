package phonebook.service.impl;

import phonebook.model.Contact;
import phonebook.model.EmailType;
import phonebook.model.PhoneNumberType;
import phonebook.service.Commands;
import phonebook.service.CommandsEnum;
import phonebook.service.UpdateCommandsEnum;

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

        System.out.print("Input contact's name -> ");
        name = in.next();

        createPhoneNumberSet();

        while (true) {

            System.out.print("Do you want create another phone number? (Y/N) -> ");
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                createPhoneNumberSet();
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("Wrong choose, input Y/N");
            } else {

                break;
            }
        }

        createEmailSet();

        System.out.print("Do you want create company name? (Y/N) -> ");

        while (true) {
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("Input company name  -> ");
                String company = in.next();
                contact.setCompany(company);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("Wrong choose, input Y/N");
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
        System.out.print("Input phone number -> ");
        String phoneNumber = in.next();
        phoneNumberSet.add(phoneNumber);

        System.out.print("Do you want to add phone number Type?(Y/N) -> ");

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
                        System.out.println("Invalid type number.");
                        break;
                }
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.print("Wrong choose, input Y/N -> ");
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
    private void phoneNumberMapBuilder(PhoneNumberType type, Set<String> phoneNumberSet,
                                       String phoneNumber) {

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
        System.out.println("Choose phone number TYPE otherwise input 9\n");

        System.out.println("INPUT " + PhoneNumberType.MOBILE.ordinal() + " FOR MOBILE");
        System.out.println("INPUT " + PhoneNumberType.HOME.ordinal() + " FOR HOME");
        System.out.println("INPUT " + PhoneNumberType.WORK.ordinal() + " FOR WORK");
        System.out.println("INPUT " + PhoneNumberType.SCHOOL.ordinal() + " FOR SCHOOL");
        System.out.print("INPUT " + PhoneNumberType.OTHER.ordinal() + " FOR OTHER");
    }

    /**
     * Map email editor
     */
    private void createEmailSet() {

        System.out.print("Do you want to add email?(Y/N) -> ");
        Set<String> emailSet = new HashSet<>();

        while (true) {
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("Input email -> ");
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
                        System.out.println("Invalid type number.");
                        break;
                }

            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("Wrong choose, input Y/N");
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
        System.out.println("Choose EMAIL TYPE otherwise press ENTER\n");

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

        System.out.print("Input name -> ");
        String name = in.next();

        System.out.println("------ CONTACTS------\n" + "\n---" + name + "---\n" + "Phone number's type ->" + map.get(name).getPhoneNumbers().keySet() + "\nPhone number -> " + map.get(name).getPhoneNumbers().values() + "\nCompany ->" + contact.getCompany() + "\nEmail's type ->" + map.get(name).getEmails().keySet() + "\nEmail ->" + map.get(name).getEmails().values());


    }

    @Override
    public void update(Map<String, Contact> map) {

        System.out.println("Input Contact name which you want to update: -> ");
        String name = in.next();
        while (!map.containsKey(name)) {
            System.out.println("Not found contact by this name. Try again! -> ");
            name = in.next();
        }

        System.out.println(name);
        System.out.println(map.get(name));

        printUpdateType();

        System.out.println("Choose number of operation which do you want to update?");
        int choice = in.nextInt();


        switch (choice) {
            case 0:
                System.out.println("Input contact's new name. ->");
                String newName = in.next();
                map.put(newName, map.get(name));
                map.remove(name);
                System.out.println("The contact's name is updated.");
                break;
            case 1:
                phoneNumberUpdate(name, map);
                System.out.println("The contact's phone number is updated.");
                break;
            case 2:
                emailUpdate(name, map);
                System.out.println("The contact's email is updated.");
                break;
            case 3:
                System.out.println("Input company's new name. ->");
                String newCompany = in.next();
                contact.setCompany(newCompany);
                System.out.println("The contact's company name is updated.");
                break;
            default:
                System.out.println("Invalid choice. Input right number!");
                break;
        }
    }

    /**
     * @param name
     * @param map
     */
    private void phoneNumberUpdate(String name, Map<String, Contact> map) {

        System.out.println("Choose phone number type.");
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
                System.out.println("Invalid type number.");
                break;
        }
    }

    private void phoneNumberUpdateCase(String name, PhoneNumberType type, Map<String, Contact> map) {

        System.out.println(map.get(name).getPhoneNumbers().get(type));
        System.out.println("Input phone number which one do you want to update. -> ");
        String phoneNumber = in.next();
        while (contact.getPhoneNumbers().get(type).contains(phoneNumber)) {
            System.out.println("Input right phone number!");
            phoneNumber = in.next();
        }
        System.out.println("Input new phone number. -> ");
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

        System.out.println("Choose email type.");
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
                System.out.println("Invalid type number.");
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
        System.out.println("Input email which one do you want to update. -> ");
        String email = in.next();

        while (contact.getEmails().get(type).contains(email)) {
            System.out.println("Input right email!");
            email = in.next();
        }
        System.out.println("Input new email. -> ");
        String newEmail = in.next();
        map.get(name).getEmails().get(type).remove(email);
        map.get(name).getEmails().get(type).add(newEmail);
    }

    /**
     * Print update type list
     */
    private void printUpdateType() {
        System.out.println("\nChoose update TYPE: \n");

        System.out.println("INPUT " + UpdateCommandsEnum.NAME_UPDATE.ordinal() + " FOR update Name");
        System.out.println("INPUT " + UpdateCommandsEnum.PHONE_NUMBER_UPDATE.ordinal() + " FOR update PhoneNumber");
        System.out.println("INPUT " + UpdateCommandsEnum.EMAIL_UPDATE.ordinal() + " FOR update Email");
        System.out.println("INPUT " + UpdateCommandsEnum.COMPANY_NAME_UPDATE.ordinal() + " FOR update company name");
    }

    /**
     * Delete contact
     *
     * @param map HashMap<> type
     */
    @Override
    public void delete(Map<String, Contact> map) {

        while (true) {

            System.out.print("Input contact's name which you want to delete -> ");
            String name = in.next();

            while (map.get(name) == null) {

                System.out.print("Contact by this name is not found!" + "\nInput right name -> ");

                name = in.next();
            }

            System.out.println("Are you sure (Y/N) -> ");
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                map.remove(name);
                System.out.print("The contact is deleted.");
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("Wrong choose, input Y/N");
            } else {

                break;
            }
        }
    }

    public static void printCommands() {

        System.out.println("INPUT " + CommandsEnum.CREATE_CONTACT.ordinal() + " FOR CREATE A NEW CONTACT");
        System.out.println("INPUT " + CommandsEnum.READ_CONTACT.ordinal() + " FOR VIEW ALL CONTACTS");
        System.out.println("INPUT " + CommandsEnum.UPDATE_CONTACT.ordinal() + " FOR UPDATE CONTACTS");
        System.out.println("INPUT " + CommandsEnum.DELETE_CONTACT.ordinal() + " FOR DELETE CONTACTS");
        System.out.println("INPUT " + CommandsEnum.EXIT.ordinal() + " FOR LOGOUT");
    }

    @Override
    public String toString() {
        return "CommandService{" + "name='" + name + '\'' + ", contact=" + contact + '}';
    }
}
