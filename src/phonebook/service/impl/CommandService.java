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

        createPhoneNumberMap();

        while (true) {

            System.out.print("Do you want create another phone number? (Y/N) -> ");
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                createPhoneNumberMap();
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("Wrong choose, input Y/N");
            } else {

                break;
            }
        }

        createEmailMap();

        System.out.print("Do you want create company name? (Y/N) -> ");

        while (true) {
            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("Input company name  -> ");
                String company = in.next();
                contact.setCompany(company);
            } if (yesNo.equalsIgnoreCase("y")) {

                createPhoneNumberMap();
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
    public void createPhoneNumberMap() {
        Map<Integer, String> phoneNumberMap = new HashMap<>();
        Integer id = 0;

        System.out.print("Input phone number -> ");
        String phoneNumber = in.next();

        id++;
        phoneNumberMap.put(id, phoneNumber);

        System.out.print("Do you want to add phone number Type?(Y/N) -> ");
        String want1 = in.next();

        if (want1.equalsIgnoreCase("y")) {

            printPhoneNumbersType();
            System.out.print(" \n-> ");
            int typeNumber = in.nextInt();

            switch (typeNumber) {
                case 0:
                    contact.getPhoneNumbers().put(PhoneNumberType.MOBILE, phoneNumberMap);
                    break;
                case 1:
                    contact.getPhoneNumbers().put(PhoneNumberType.HOME, phoneNumberMap);
                    break;
                case 2:
                    contact.getPhoneNumbers().put(PhoneNumberType.WORK, phoneNumberMap);
                    break;
                case 3:
                    contact.getPhoneNumbers().put(PhoneNumberType.SCHOOL, phoneNumberMap);
                    break;
                case 4:
                    contact.getPhoneNumbers().put(PhoneNumberType.OTHER, phoneNumberMap);
                    break;
                default:
                    System.out.println("Invalid type number.");
                    break;
            }
        } else {

            contact.getPhoneNumbers().put(PhoneNumberType.OTHER, phoneNumberMap);
        }
    }

    /**
     * Print phone type number list
     */
    public void printPhoneNumbersType() {
        System.out.println("Choose phone number TYPE otherwise press ENTER\n");

        System.out.println("INPUT " + PhoneNumberType.MOBILE.ordinal() + " FOR MOBILE");
        System.out.println("INPUT " + PhoneNumberType.HOME.ordinal() + " FOR HOME");
        System.out.println("INPUT " + PhoneNumberType.WORK.ordinal() + " FOR WORK");
        System.out.println("INPUT " + PhoneNumberType.SCHOOL.ordinal() + " FOR SCHOOL");
        System.out.print("INPUT " + PhoneNumberType.OTHER.ordinal() + " FOR OTHER");
    }

    /**
     * Map email editor
     */
    public void createEmailMap() {

        Map<Integer, String> emailMap = new HashMap<>();
        Integer id = 0;

        System.out.print("Do you want to add email?(Y/N) -> ");
        String want = in.next();

        if (want.equalsIgnoreCase("y")) {

            System.out.print("Input email -> ");
            String email = in.next();

            id++;
            emailMap.put(id, email);

            printEmailType();
            System.out.print("->");
            int typeNUmber = in.nextInt();

            switch (typeNUmber) {
                case 0:
                    contact.getEmails().put(EmailType.GMAIL, emailMap);
                    break;
                case 1:
                    contact.getEmails().put(EmailType.ICLOUD, emailMap);
                    break;
                case 2:
                    contact.getEmails().put(EmailType.OTHER, emailMap);
                    break;
                default:
                    System.out.println("Invalid type number.");
                    break;
            }
        } else {
            emailMap.put(0, "--------");
            contact.getEmails().put(EmailType.EMPTY, emailMap);
        }
    }

    /**
     * Print email type list
     */
    public void printEmailType() {
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

        System.out.println("------ CONTACTS------\n" + "\n---" + name + "---\n" + "Phone number's type ->"
                + map.get(name).getPhoneNumbers().keySet() +
                "\nPhone number -> " + map.get(name).getPhoneNumbers().values() +
                "\nCompany ->" + contact.getCompany() + "\nEmail's type ->"
                + map.get(name).getEmails().keySet()
                + "\nEmail ->" + map.get(name).getEmails().values());


    }

    @Override
    public void update(Map<String, Contact> map) {

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

                System.out.print("Contact by this name is not found!" +
                        "\nInput right name -> ");

                name = in.next();
            }

            System.out.println("Are you sure (Y/N) -> ");
            String nameDel = in.next();

            if (nameDel.equalsIgnoreCase("y")) {

                map.remove(name);
                System.out.print("The contact is deleted.");
            }

            return;
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
        return "CommandService{" +
                "name='" + name + '\'' +
                ", contact=" + contact +
                '}';
    }
}
