package controller.impl;


import controller.ApplicationController;
import model.Contact;
import model.enums.PhoneNumberType;

import java.util.*;

public class PhoneNumberControllerImpl {
    Scanner in = new Scanner(System.in);
    Contact contact = new Contact();

    /**
     * Build phone number
     *
     * @param type        PhoneNumberType Enum type
     * @param phoneNumber String type
     */
    public void phoneNumberMapBuilder(Map<PhoneNumberType, Set<String>> phoneNumbers,
                                      PhoneNumberType type, String phoneNumber) {
/*
        if (!contact.getPhoneNumbers().containsKey(type)) {
            contact.getPhoneNumbers().put(type, phoneNumberSet);
        } else {

            contact.getPhoneNumbers().get(type).add(phoneNumber);
        }
        */
        if (phoneNumbers.containsKey(type)) {
            phoneNumbers.get(type).add(phoneNumber);
        } else {
            Set<String> phoneNumbersValues = new HashSet<>();
            phoneNumbersValues.add(phoneNumber);
            phoneNumbers.put(type, phoneNumbersValues);
        }
    }

    /**
     * phone number update
     *
     * @param name String type
     * @param map  Map<String, Contact> typr
     */
    public void phoneNumberUpdate(String name, Map<String, Contact> map) {

        ApplicationController.printPhoneNumbersType();

        boolean isChoose = true;
        String typeNumber;

        while (isChoose) {

            isChoose = false;
            System.out.println("\u001B[34m" + "Choose phone number type. -> ");

            typeNumber = in.next();

            switch (typeNumber) {
                case "0":
                    phoneNumberUpdateCase(name, PhoneNumberType.MOBILE, map);
                    break;
                case "1":
                    phoneNumberUpdateCase(name, PhoneNumberType.HOME, map);
                    break;
                case "2":
                    phoneNumberUpdateCase(name, PhoneNumberType.WORK, map);
                    break;
                case "3":
                    phoneNumberUpdateCase(name, PhoneNumberType.SCHOOL, map);
                    break;
                case "4":
                    phoneNumberUpdateCase(name, PhoneNumberType.OTHER, map);
                    break;
                default:
                    System.out.println("\u001B[31m" + "Invalid type number.");
                    isChoose = true;
            }
        }
    }

    /**
     * @param name String type
     * @param type PhoneNumberType type
     * @param map  Map<String, Contact> type
     */
    public void phoneNumberUpdateCase(String name, PhoneNumberType type, Map<String, Contact> map) {

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
     * delete phone number
     *
     * @param type PhoneNumberType type
     * @param map  Map<String, Contact> type
     */
    public void deleteForPhoneNumber(PhoneNumberType type, Map<String, Contact> map) {

        String number;

        while (true) {

            System.out.print("Input phone numbers -> ");
            number = in.next();
            if (!contact.getPhoneNumbers().get(type).contains(number)) {

                contact.getPhoneNumbers().get(type).remove(number);

                break;
            } else {

                System.out.println("Incorrect number");
            }

        }

    }

    /**
     * Map phone number editor
     */
    public Map<PhoneNumberType, Set<String>> createPhoneNumbers() {

        Map<PhoneNumberType, Set<String>> phoneNumbers = new HashMap<>();
        System.out.print("\u001B[34m" + "Input phone number -> ");
        String phoneNumber = in.next();

        Set<String> phoneNums = new HashSet<>();
        phoneNums.add(phoneNumber);
        phoneNumbers.put(PhoneNumberType.MOBILE, phoneNums);

        System.out.print("\u001B[34m" + "Do you want to add phone number Type?(Y/N) -> ");

        String yesNo;

        while (true) {
            yesNo = in.next();
            if (yesNo.equalsIgnoreCase("y")) {

                ApplicationController.printPhoneNumbersType();
                System.out.print(" \n-> ");

                boolean isTypeChoose = true;
                String typeNumber;

                while (isTypeChoose) {

                    isTypeChoose = false;

                    typeNumber = in.next();

                    switch (typeNumber) {

                        case "0":
                            phoneNumberMapBuilder(phoneNumbers, PhoneNumberType.MOBILE, phoneNumber);
                            break;
                        case "1":
                            phoneNumberMapBuilder(phoneNumbers, PhoneNumberType.HOME, phoneNumber);
                            break;
                        case "2":
                            phoneNumberMapBuilder(phoneNumbers, PhoneNumberType.WORK, phoneNumber);
                            break;
                        case "3":
                            phoneNumberMapBuilder(phoneNumbers, PhoneNumberType.SCHOOL, phoneNumber);
                            break;
                        case "4":
                            phoneNumberMapBuilder(phoneNumbers, PhoneNumberType.OTHER, phoneNumber);
                            break;
                        default:
                            System.out.println("\u001B[31m" + "Invalid type number.");
                            System.out.print("\u001B[34m" + "Input new number -> ");
                            isTypeChoose = true;
                    }
                }

                break;

            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.print("\u001B[31m" + "Wrong choose! Input Y/N-> ");
                System.out.print("\u001B[34m" + "Input Y/N-> ");
            } else {

                phoneNumberMapBuilder(phoneNumbers, PhoneNumberType.OTHER, phoneNumber);
                break;
            }
        }

        return phoneNumbers;
    }
}
