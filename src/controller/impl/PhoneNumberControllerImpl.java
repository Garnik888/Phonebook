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
     * @param type           PhoneNumberType Enum type
     * @param phoneNumberSet Set<String> type
     * @param phoneNumber    String type
     */
    public void phoneNumberMapBuilder(PhoneNumberType type, Set<String> phoneNumberSet, String phoneNumber) {

        if (!contact.getPhoneNumbers().containsKey(type)) {
            contact.getPhoneNumbers().put(type, phoneNumberSet);
        } else {

            contact.getPhoneNumbers().get(type).add(phoneNumber);
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
    public void createPhoneNumberSet() {

        Set<String> phoneNumberSet = new HashSet<>();
        System.out.print("\u001B[34m" + "Input phone number -> ");
        String phoneNumber = in.next();

        phoneNumberSet.add(phoneNumber);

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
                            phoneNumberMapBuilder(PhoneNumberType.MOBILE, phoneNumberSet, phoneNumber);
                            break;
                        case "1":
                            phoneNumberMapBuilder(PhoneNumberType.HOME, phoneNumberSet, phoneNumber);
                            break;
                        case "2":
                            phoneNumberMapBuilder(PhoneNumberType.WORK, phoneNumberSet, phoneNumber);
                            break;
                        case "3":
                            phoneNumberMapBuilder(PhoneNumberType.SCHOOL, phoneNumberSet, phoneNumber);
                            break;
                        case "4":
                            phoneNumberMapBuilder(PhoneNumberType.OTHER, phoneNumberSet, phoneNumber);
                            break;
                        default:
                            System.out.println("\u001B[31m" + "Invalid type number.");
                            System.out.print("\u001B[34m" + "Input new number -> ");
                            isTypeChoose = true;
                    }
                }

            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.print("\u001B[31m" + "Wrong choose! Input Y/N-> ");
            } else {

                phoneNumberMapBuilder(PhoneNumberType.OTHER, phoneNumberSet, phoneNumber);
                break;
            }

        }
    }

}
