package controller.impl;


import controller.ApplicationController;
import model.Contact;
import model.enums.EmailType;
import model.enums.PhoneNumberType;

import java.util.*;

public class PhoneNumberControllerImpl {


    Scanner in = new Scanner(System.in);


    /**
     * phone number update
     *
     * @param name String type
     * @param map  Map<String, Contact> typr
     */
    public void phoneNumberUpdate(String name, Map<String, Contact> map, Map<PhoneNumberType, Set<String>> phoneNumbers) {

        ApplicationController.printPhoneNumbersType();

        boolean isChoose = true;
        String typeNumber;

        while (isChoose) {

            isChoose = false;
            System.out.println("\u001B[34m" + "Choose phone number type. -> ");

            typeNumber = in.next();

            switch (typeNumber) {
                case "0":
                    phoneNumberUpdateCase(name, PhoneNumberType.MOBILE, map,  phoneNumbers);
                    break;
                case "1":
                    phoneNumberUpdateCase(name, PhoneNumberType.HOME, map,phoneNumbers);
                    break;
                case "2":
                    phoneNumberUpdateCase(name, PhoneNumberType.WORK, map,phoneNumbers);
                    break;
                case "3":
                    phoneNumberUpdateCase(name, PhoneNumberType.SCHOOL, map,phoneNumbers);
                    break;
                case "4":
                    phoneNumberUpdateCase(name, PhoneNumberType.OTHER, map,phoneNumbers);
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
    public void phoneNumberUpdateCase(String name, PhoneNumberType type, Map<String, Contact> map, Map<PhoneNumberType, Set<String>> phoneNumbers) {

        System.out.println(map.get(name).getPhoneNumbers().get(type));
        System.out.print("\u001B[34m" + "Input phone number which one do you want to update. -> ");
        String phoneNumber = in.next();
        while (!phoneNumbers.get(type).contains(phoneNumber)) {
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
    public void deleteForPhoneNumber(PhoneNumberType type, Map<String, Contact> map, Map<PhoneNumberType, Set<String>> phoneNumbers) {

        String number;

        while (true) {

            System.out.print("Input phone numbers -> ");
            number = in.next();
            if (!phoneNumbers.get(type).contains(number)) {

                phoneNumbers.get(type).remove(number);

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
        boolean isTypeChoose;

        System.out.print("\u001B[34m" + "Do you want to add phone number Type?(Y/N) -> ");

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

                            if (phoneNumbers.containsKey(PhoneNumberType.MOBILE)){
                                phoneNumbers.get(PhoneNumberType.MOBILE).add(phoneNumber);
                            }else {
                                mobileSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.MOBILE, mobileSet);
                            }
                            break;
                        case "1":
                            if (phoneNumbers.containsKey(PhoneNumberType.HOME)){
                                phoneNumbers.get(PhoneNumberType.HOME).add(phoneNumber);
                            }else {
                                homeSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.HOME, homeSet);
                            }
                            break;
                        case "2":
                            if (phoneNumbers.containsKey(PhoneNumberType.WORK)){
                                phoneNumbers.get(PhoneNumberType.WORK).add(phoneNumber);
                            }else {
                                workSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.WORK, workSet);
                            }
                            break;
                        case "3":
                            if (phoneNumbers.containsKey(PhoneNumberType.SCHOOL)){
                                phoneNumbers.get(PhoneNumberType.SCHOOL).add(phoneNumber);
                            }else {
                                schoolSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.SCHOOL, schoolSet);
                            }
                            break;
                        case "4":
                            if (phoneNumbers.containsKey(PhoneNumberType.OTHER)){
                                phoneNumbers.get(PhoneNumberType.OTHER).add(phoneNumber);
                            }else {
                                otherSet.add(phoneNumber);
                                phoneNumbers.put(PhoneNumberType.OTHER, otherSet);
                            }
                            break;
                        default:
                            System.out.println("\u001B[31m" + "Invalid type number.");
                            System.out.print("\u001B[34m" + "Input new number -> ");
                            isTypeChoose = true;
                            break;
                    }
                }
                break;

            } else if (yesNo.equalsIgnoreCase("n")) {
                if (phoneNumbers.containsKey(PhoneNumberType.OTHER)){
                    phoneNumbers.get(PhoneNumberType.OTHER).add(phoneNumber);
                }else {
                    otherSet.add(phoneNumber);
                    phoneNumbers.put(PhoneNumberType.OTHER, otherSet);
                } break;
            } else {
                System.out.print("\u001B[31m" + "Wrong choose! Input Y/N-> ");
                System.out.print("\u001B[34m" + "Input Y/N-> ");
            }
        }

    }
}
