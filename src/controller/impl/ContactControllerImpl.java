package controller.impl;

import controller.ApplicationController;
import model.Contact;
import model.enums.EmailType;
import model.enums.PhoneNumberType;

import java.io.*;
import java.util.*;

public class ContactControllerImpl implements Serializable {

    Scanner in = new Scanner(System.in);
    PhoneNumberControllerImpl phoneNumbers = new PhoneNumberControllerImpl();
    EmailControllerImpl emailController = new EmailControllerImpl();
    String name;
    String company;

    public void add(Map<String, Contact> map) {


        System.out.print("\u001B[34m" + "Input contact's name -> ");
        name = in.next();

        Map<PhoneNumberType, Set<String>> phoneNumbers = new HashMap<>();
        Map<EmailType, Set<String>> emails = new HashMap<>();
        this.phoneNumbers.createPhoneNumbers(phoneNumbers);
        String yesNo;

        while (true) {

            System.out.print("\u001B[34m" + "Do you want add another phone number? (Y/N) -> ");
            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                this.phoneNumbers.createPhoneNumbers(phoneNumbers);

            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");

            } else {
                break;
            }
        }

        emailController.createEmailSet(emails);

        while (true) {

            System.out.print("\u001B[34m" + "Do you want add another email? (Y/N) -> ");
            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                emailController.createEmailSet(emails);

            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

        System.out.print("\u001B[34m" + "Do you want create company name? (Y/N) -> ");

        while (true) {

            yesNo = in.next();
            in.nextLine();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("\u001B[34m" + "Input company name  -> ");
                company = in.nextLine();
                break;

            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }
        Contact contact = new Contact(phoneNumbers, company, emails);
        map.put(name, contact);
    }

    public void search(Map<String, Contact> map) {
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
                + "Phone number(s) ->" + map.get(name).getPhoneNumbers() + "\nCompany ->"
                + map.get(name).getCompany() + "\nEmail(s) ->" + map.get(name).getEmails());


    }


    public void modify(Map<String, Contact> map, Map<EmailType, Set<String>> emails, Map<PhoneNumberType, Set<String>> phoneNumbers) {

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

        ApplicationController.printUpdateType();

        System.out.println("\u001B[34m" + "Choose number of operation which do you want to update?");
        int choice;

        try {

            choice = in.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("\u001B[34m" + "Input contact's new name. ->");
                    String newName = in.next();
                    map.put(newName, map.get(name));
                    map.remove(name);
                    System.out.println("\u001B[36m" + "The contact's name is updated.");
                    break;
                case 1:
                    this.phoneNumbers.phoneNumberUpdate(name, map, phoneNumbers);
                    System.out.println("\u001B[36m" + "The contact's phone number is updated.");
                    break;
                case 2:
                    System.out.println("\u001B[36m" + "If tou want delete ALL phone typ input 0");
                    System.out.println("\u001B[36m" + "If tou want delete phone number input 1");

                    while (true) {

                        int del = in.nextInt();

                        if (del == 0) {

                            ApplicationController.printPhoneNumbersType();


                            while (true) {
                                System.out.print(" \n-> ");
                                int typeNumber;

                                try {
                                    typeNumber = in.nextInt();

                                    switch (typeNumber) {
                                        case 0:
                                            if (!phoneNumbers.containsKey(PhoneNumberType.MOBILE)) {
                                                phoneNumbers.remove(PhoneNumberType.MOBILE);
                                            }
                                            break;
                                        case 1:
                                            if (!phoneNumbers.containsKey(PhoneNumberType.HOME)) {
                                                phoneNumbers.remove(PhoneNumberType.HOME);
                                            }
                                            break;
                                        case 2:
                                            if (!phoneNumbers.containsKey(PhoneNumberType.WORK)) {
                                                phoneNumbers.remove(PhoneNumberType.WORK);
                                            }
                                            break;
                                        case 3:
                                            if (!phoneNumbers.containsKey(PhoneNumberType.SCHOOL)) {
                                                phoneNumbers.remove(PhoneNumberType.SCHOOL);
                                            }
                                            break;
                                        case 4:
                                            if (!phoneNumbers.containsKey(PhoneNumberType.OTHER)) {
                                                phoneNumbers.remove(PhoneNumberType.OTHER);
                                            }
                                            break;
                                        default:
                                            System.out.println("\u001B[31m" + "Invalid type number.");
                                            continue;
                                    }
                                } catch (InputMismatchException ime) {

                                    System.out.println("input number from 0 to 4");
                                }
                                break;
                            }

                        } else if (del == 1) {

                            System.out.println(phoneNumbers);
                            System.out.print("Choose phone numbers type");
                            ApplicationController.printPhoneNumbersType();

                            while (true) {
                                System.out.print(" \n-> ");
                                int typeNumber;

                                try {
                                    typeNumber = in.nextInt();

                                    switch (typeNumber) {
                                        case 0:

                                            this.phoneNumbers.deleteForPhoneNumber(PhoneNumberType.MOBILE, map, phoneNumbers);
                                            break;
                                        case 1:
                                            this.phoneNumbers.deleteForPhoneNumber(PhoneNumberType.HOME, map, phoneNumbers);
                                            break;
                                        case 2:
                                            this.phoneNumbers.deleteForPhoneNumber(PhoneNumberType.WORK, map, phoneNumbers);
                                            break;
                                        case 3:
                                            this.phoneNumbers.deleteForPhoneNumber(PhoneNumberType.SCHOOL, map, phoneNumbers);
                                            break;
                                        case 4:
                                            this.phoneNumbers.deleteForPhoneNumber(PhoneNumberType.OTHER, map, phoneNumbers);
                                            break;
                                        default:
                                            System.out.println("\u001B[31m" + "Invalid type number.");
                                            continue;
                                    }
                                } catch (InputMismatchException ime) {

                                    System.out.println("input number from 0 to 4");
                                }

                                break;
                            }

                            break;
                        }
                    }
                case 3:
                    emailController.emailUpdate(name, map, emails);
                    System.out.println("\u001B[36m" + "The contact's email is updated.");
                    break;
                case 4:
                    System.out.println("\u001B[36m" + "If tou want delete ALL email typ input 0");
                    System.out.println("\u001B[36m" + "If tou want delete email input 1");

                    while (true) {

                        int del = in.nextInt();

                        if (del == 0) {

                            ApplicationController.printEmailType();

                            while (true) {
                                System.out.print(" \n-> ");
                                int typeEmail;

                                try {
                                    typeEmail = in.nextInt();

                                    switch (typeEmail) {
                                        case 0:
                                            if (!emails.containsKey(EmailType.GMAIL)) {
                                                emails.remove(EmailType.GMAIL);
                                            }
                                            break;
                                        case 1:
                                            if (!emails.containsKey(EmailType.ICLOUD)) {
                                                emails.remove(EmailType.ICLOUD);
                                            }
                                            break;
                                        case 2:
                                            if (!emails.containsKey(EmailType.OTHER)) {
                                                emails.remove(EmailType.OTHER);
                                            }
                                            break;
                                        default:
                                            System.out.println("\u001B[31m" + "Invalid type number.");
                                            continue;
                                    }
                                } catch (InputMismatchException ime) {

                                    System.out.println("input number from 0 to 4");
                                }
                                break;
                            }

                        } else if (del == 1) {

                            System.out.println(emails);
                            System.out.print("Choose email type");
                            ApplicationController.printEmailType();

                            while (true) {
                                System.out.print(" \n-> ");
                                int typeNumber;

                                try {
                                    typeNumber = in.nextInt();

                                    switch (typeNumber) {
                                        case 0:
                                            emailController.deleteForEmail(EmailType.GMAIL, map, emails);
                                            break;
                                        case 1:
                                            emailController.deleteForEmail(EmailType.ICLOUD, map, emails);
                                            break;
                                        case 2:
                                            emailController.deleteForEmail(EmailType.OTHER, map, emails);
                                            break;

                                        default:
                                            System.out.println("\u001B[31m" + "Invalid type number.");
                                            continue;
                                    }
                                } catch (InputMismatchException ime) {

                                    System.out.println("input number from 0 to 4");
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
                    company = newCompany;
                    System.out.println("\u001B[36m" + "The contact's company name is updated.");
                    break;
                case 6:
                    System.out.println("\u001B[34m" + "Input company's new name. ->");
                    String company = in.next();
                    company = null;
                    break;
                default:
                    System.out.println("\u001B[31m" + "Invalid choice. Input right number!");
                    break;
            }
        } catch (InputMismatchException ime) {

            System.out.println("input number from 0 to 4");
        }
    }

    /**
     * Delete contact
     *
     * @param map HashMap<> type
     */

    public void remove(Map<String, Contact> map) {

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
}
