package controller.impl;

import Validators.Validators;
import controller.ApplicationController;
import model.Contact;
import model.enums.EmailType;
import model.enums.PhoneNumberType;
import start.Menu;

import java.io.*;
import java.util.*;

public class ContactControllerImpl implements Serializable {

    Scanner in = new Scanner(System.in);
    PhoneNumberControllerImpl phoneNumbersImpl = new PhoneNumberControllerImpl();
    EmailControllerImpl emailController = new EmailControllerImpl();


    public void add(Map<String, Contact> map, String company, String name) {


        System.out.print("\u001B[34m" + "Input contact's name -> ");
        name = in.next();

        Map<PhoneNumberType, Set<String>> phoneNumbers = new HashMap<>();
        Map<EmailType, Set<String>> emails = new HashMap<>();
        this.phoneNumbersImpl.createPhoneNumbers(phoneNumbers);
        String yesNo;

        while (true) {

            System.out.print("\u001B[34m" + "Do you want add another phone number? (Y/N) -> ");
            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                this.phoneNumbersImpl.createPhoneNumbers(phoneNumbers);

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

    public void modify(Map<String, Contact> map) {

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

        String choice;

        boolean choiceType = true;

        while (choiceType) {

            choiceType = false;
            choice = in.next();

            switch (choice) {

                case "0":

                    System.out.print("\u001B[34m" + "Input contact new name -> ");
                    String newName = in.next();
                    while (!Validators.checkNameSize(newName)) {
                        System.out.println("\u001B[31m" + "Name's max size is 20 characters. Input again -> ");
                    }

                    map.put(newName, map.get(name));

                    map.remove(name);

                    System.out.println("\u001B[36m" + "The contact's name is updated.");
                    break;

                case "1":
                    phoneNumbersImpl.phoneNumberUpdate(name, map);
                    System.out.println("\u001B[36m" + "The contact's phone number is updated.");
                    break;

                case "2":

                    System.out.println(map.get(name).getPhoneNumbers());
                    ApplicationController.printPhoneNumbersType();

                    String typeNumber;

                    boolean isType = true;

                    while (isType) {

                        isType = false;

                        System.out.print(" \n-> ");

                        typeNumber = in.next();

                        switch (typeNumber) {
                            case "0":
                                this.phoneNumbersImpl.deleteForPhoneNumber(PhoneNumberType.MOBILE, map, name);
                                break;
                            case "1":
                                this.phoneNumbersImpl.deleteForPhoneNumber(PhoneNumberType.HOME, map, name);
                                break;
                            case "2":
                                this.phoneNumbersImpl.deleteForPhoneNumber(PhoneNumberType.WORK, map, name);
                                break;
                            case "3":
                                this.phoneNumbersImpl.deleteForPhoneNumber(PhoneNumberType.SCHOOL, map, name);
                                break;
                            case "4":
                                this.phoneNumbersImpl.deleteForPhoneNumber(PhoneNumberType.OTHER, map, name);
                                break;
                            default:
                                System.out.println("\u001B[31m" + "Invalid type number.");
                                isType = true;
                        }
                    }

                    break;

                case "3":

                    emailController.emailUpdate(name, map);
                    System.out.println("\u001B[36m" + "The contact's email is updated.");

                    break;

                case "4":
                    System.out.println(map.get(name).getEmails());
                    System.out.print("Choose email type");
                    ApplicationController.printEmailType();

                    isType = true;

                    while (isType) {

                        isType = false;

                        System.out.print(" \n-> ");

                        typeNumber = in.next();

                        switch (typeNumber) {
                            case "0":
                                emailController.deleteForEmail(EmailType.GMAIL, map, name);
                                break;
                            case "1":
                                emailController.deleteForEmail(EmailType.ICLOUD, map, name);
                                break;
                            case "2":
                                emailController.deleteForEmail(EmailType.OTHER, map, name);
                                break;

                            default:
                                System.out.println("\u001B[31m" + "Invalid type number.");
                                isType = true;
                        }

                    }
                    break;

                case "5":
                    System.out.println(map.get(name).getCompany());
                    System.out.println("\u001B[34m" + "Input company's new name. ->");
                    String newCompany = in.next();
                    while (!Validators.checkNameSize(newCompany)) {
                        System.out.print("Name's max size is 20 characters. Input again ->");
                        newCompany = in.next();
                    }
                    map.get(name).setCompany(newCompany);
                    System.out.println("\u001B[36m" + "The contact's company name is updated.");
                    break;

                case "6":
                    System.out.println(map.get(name).getCompany());
                    System.out.println("\u001B[34m" + "Input company's  name. ->");
                    name = in.next();
                    map.get(name).setCompany("");
                    System.out.println("The company's name is deleted!");
                    break;
                default:
                    System.out.println("\u001B[31m" + "Invalid choice. Input right number!");
                    choiceType =true;

            }
        }
    }

    /**
     * Delete contact
     *
     * @param map HashMap<> type
     */

    public void remove(Map<String, Contact> map) {

        Map<String, Contact> map1 = new TreeMap<>(map);

        for (Map.Entry<String, Contact> contactName : map1.entrySet()) {
            System.out.println(contactName.getKey());
        }

        while (true) {

            System.out.print("\u001B[34m" + "Input contact's name which you want to delete -> ");
            String name = in.next();

            while (map.get(name) == null) {

                System.out.print("Contact by this name is not found!" + "\nInput right name -> ");

                name = in.next();
            }

            System.out.print("\u001B[34m" + "Are you sure (Y/N) -> ");
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
