package controller.impl;


import controller.ApplicationController;
import model.Contact;
import model.enums.PhoneNumberType;
import Validators.Validators;

import java.util.*;

public class PhoneNumberControllerImpl {


    Scanner in = new Scanner(System.in);

    /**
     * phone number update
     *
     * @param name String type
     * @param map  Map<String, Contact> typr
     */
    public void phoneNumberUpdate(String name, Map<String, Contact> map) {

        boolean isChoose = true;
        String typeNumber;

        ApplicationController.printPhoneNumbersType();

        while (isChoose) {

            isChoose = false;

            System.out.print("\u001B[34m" + " -> ");

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
    public void phoneNumberUpdateCase(String name, PhoneNumberType type,
                                      Map<String, Contact> map) {

        System.out.println(map.get(name).getPhoneNumbers().get(type));
        System.out.print("\u001B[34m" + "Input phone number which one do you want to update. -> ");
        String phoneNumber = in.next();

        while (!map.get(name).getPhoneNumbers().get(type).contains(phoneNumber)) {
            System.out.print("\u001B[34m" + "Input right phone number! -> ");
            phoneNumber = in.next();
        }

        System.out.print("\u001B[34m" + "Input new phone number. -> ");
        String newPhoneNumber = in.next();

        while (!Validators.checkPhoneNumber(newPhoneNumber)) {
            System.out.println("Incorrect phone number! Input again ->");
            newPhoneNumber = in.next();
        }

        map.get(name).getPhoneNumbers().get(type).remove(phoneNumber);
        map.get(name).getPhoneNumbers().get(type).add(newPhoneNumber);
    }

    /**
     * delete phone number
     *
     * @param type PhoneNumberType type
     * @param map  Map<String, Contact> type
     */
    public void deleteForPhoneNumber(PhoneNumberType type, Map<String, Contact> map, String name) {

        String number;

        while (true) {
            System.out.print("Input phone numbers -> ");
            number = in.next();

            if (map.get(name).getPhoneNumbers().get(type).contains(number)) {

                map.get(name).getPhoneNumbers().get(type).remove(number);

                break;
            } else {

                System.out.println("Incorrect number");
            }

        }

    }

    /**
     * Map phone number editor
     */
    public void createPhoneNumbers(Map<PhoneNumberType, Set<String>> phoneNumbers) {

        Set<String> mobileSet = new HashSet<>();
        Set<String> homeSet = new HashSet<>();
        Set<String> workSet = new HashSet<>();
        Set<String> schoolSet = new HashSet<>();
        Set<String> otherSet = new HashSet<>();

        System.out.print("\u001B[34m" + "Input phone number -> ");
        String phoneNumber = in.next();

        while (!Validators.checkPhoneNumber(phoneNumber)) {
            System.out.print("\u001B[31m" + "Incorrect phone number! Input again ->" + "\u001B[0m");
            phoneNumber = in.next();
        }
        boolean isTypeChoose;

        System.out.print("\u001B[34m" + "Do you want to add phone number Type?(Y/N) -> " + "\u001B[0m");

        String yesNo;

        while (true) {
            yesNo = in.next();
            if (yesNo.equalsIgnoreCase("y")) {

                ApplicationController.printPhoneNumbersType();
                System.out.print(" \n-> ");
                isTypeChoose = true;
                String typeNumber;

                while (isTypeChoose) {

                    isTypeChoose = false;

                    typeNumber = in.next();

                    switch (typeNumber) {

                        case "0":

                            if (phoneNumbers.containsKey(PhoneNumberType.MOBILE)) {
                                phoneNumbers.get(PhoneNumberType.MOBILE).add(phoneNumber);
                            } else {
                                mobileSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.MOBILE, mobileSet);
                            }
                            break;
                        case "1":
                            if (phoneNumbers.containsKey(PhoneNumberType.HOME)) {
                                phoneNumbers.get(PhoneNumberType.HOME).add(phoneNumber);
                            } else {
                                homeSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.HOME, homeSet);
                            }
                            break;
                        case "2":
                            if (phoneNumbers.containsKey(PhoneNumberType.WORK)) {
                                phoneNumbers.get(PhoneNumberType.WORK).add(phoneNumber);
                            } else {
                                workSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.WORK, workSet);
                            }
                            break;
                        case "3":
                            if (phoneNumbers.containsKey(PhoneNumberType.SCHOOL)) {
                                phoneNumbers.get(PhoneNumberType.SCHOOL).add(phoneNumber);
                            } else {
                                schoolSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.SCHOOL, schoolSet);
                            }
                            break;
                        case "4":
                            if (phoneNumbers.containsKey(PhoneNumberType.OTHER)) {
                                phoneNumbers.get(PhoneNumberType.OTHER).add(phoneNumber);
                            } else {
                                otherSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.OTHER, otherSet);
                            }
                            break;
                        default:
                            System.out.println("\u001B[31m" + "Invalid type number." + "\u001B[0m");
                            System.out.print("\u001B[34m" + "Input new number -> " + "\u001B[0m");
                            isTypeChoose = true;
                            break;
                    }
                }
                break;

            } else if (yesNo.equalsIgnoreCase("n")) {
                if (phoneNumbers.containsKey(PhoneNumberType.OTHER)) {
                    phoneNumbers.get(PhoneNumberType.OTHER).add(phoneNumber);
                } else {
                    otherSet.add(phoneNumber);
                    phoneNumbers.put(PhoneNumberType.OTHER, otherSet);
                }
                break;
            } else {
                System.out.print("\u001B[31m" + "Wrong choose! Input Y/N-> ");
                System.out.print("\u001B[34m" + "Input Y/N-> ");
            }
        }

    }
}
