package controller.impl;

import Validators.Validators;
import controller.ApplicationController;
import model.Contact;
import model.enums.EmailType;
import model.enums.PhoneNumberType;

import java.util.*;

public class EmailControllerImpl {

    Scanner in = new Scanner(System.in);


    /**
     * Update email
     *
     * @param name String type
     * @param map  Map<String, Contact> map) type
     */
    public void emailUpdate(String name, Map<String, Contact> map) {
        ApplicationController.printEmailType();

        String type;

        String typeEmail;
        boolean isTypeChoose = true;

        while (isTypeChoose) {

            isTypeChoose = false;

            System.out.println("\u001B[34m" + "Choose email type -> ");

            type = in.next();

            switch (type) {
                case "0":
                    typeEmail = "GMAIL";
                    if (EmailType.GMAIL.equals(EmailType.valueOf(typeEmail))) {
                        emailUpdateCase(name, EmailType.GMAIL, map);
                    }
                    break;
                case "1":
                    typeEmail = "ICLOUD";
                    if (EmailType.ICLOUD.equals(EmailType.valueOf(typeEmail))) {
                        emailUpdateCase(name, EmailType.ICLOUD, map);
                    }
                    break;
                case "2":
                    typeEmail = "OTHER";
                    if (EmailType.OTHER.equals(EmailType.valueOf(typeEmail))) {
                        emailUpdateCase(name, EmailType.OTHER, map);
                    }
                default:
                    System.out.println("\u001B[31m" + "Invalid type number.");
                    isTypeChoose = true;
            }
        }
    }

    /**
     * Email update case
     *
     * @param type EmailType type
     * @param map  Map<String, Contact> type
     */
    public void emailUpdateCase(String name, EmailType type, Map<String, Contact> map) {
        System.out.println(map.get(name).getEmails().get(type));
        System.out.print("\u001B[34m" + "Input email which one do you want to update. -> ");
        String email = in.next();

        while (!map.get(name).getEmails().get(type).contains(email)) {
            System.out.print("\u001B[34m" + "Input right email! -> ");
            email = in.next();
        }
        System.out.print("\u001B[34m" + "Input new email. -> ");
        String newEmail = in.next();
        while (!Validators.checkEmail(newEmail)) {
            System.out.print("Incorrect email! Input again ->");
            newEmail = in.next();
        }
        map.get(name).getEmails().get(type).remove(email);
        map.get(name).getEmails().get(type).add(newEmail);
    }

    /**
     * delete email
     *
     * @param type EmailType type
     * @param map  Map<String, Contact> type
     */
    public void deleteForEmail(EmailType type, Map<String, Contact> map, String name) {

        String email;

        while (true) {

            System.out.print("Input email -> ");
            email = in.next();
            if (!map.get(name).getEmails().get(type).contains(email)) {

                map.get(name).getEmails().get(type).remove(email);

                break;
            } else {

                System.out.println("Incorrect email!");
            }

        }
    }

    /**
     * Map email editor
     */
    public void createEmailSet(Map<EmailType, Set<String>> emails) {
        Set<String> gmailSet = new HashSet<>();
        Set<String> iCloudSet = new HashSet<>();
        Set<String> otherSet = new HashSet<>();
        boolean isTypeChoose;

        System.out.print("\u001B[34m" + "Do you want to add email?(Y/N) -> ");

        String yesNo;

        while (true) {
            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("\u001B[34m" + "Input email -> ");
                String email = in.next();

                while (!Validators.checkEmail(email)) {
                    System.out.print("Incorrect email! Input again ->");
                    email = in.next();
                }

                ApplicationController.printEmailType();
                System.out.print("->");

                isTypeChoose = true;
                String typeNumber;

                while (isTypeChoose) {

                    isTypeChoose = false;
                    typeNumber = in.next();
                    switch (typeNumber) {
                        case "0":
                            if (emails.containsKey(EmailType.GMAIL)) {
                                emails.get(EmailType.GMAIL).add(email);
                            } else {
                                gmailSet.add(email);
                                emails.put(EmailType.GMAIL, gmailSet);
                            }
                            break;
                        case "1":
                            if (emails.containsKey(EmailType.ICLOUD)) {
                                emails.get(EmailType.ICLOUD).add(email);
                            } else {
                                iCloudSet.add(email);
                                emails.put(EmailType.ICLOUD, iCloudSet);
                            }
                            break;
                        case "2":
                            if (emails.containsKey(EmailType.OTHER)) {
                                emails.get(EmailType.OTHER).add(email);
                            } else {
                                otherSet.add(email);
                                emails.put(EmailType.OTHER, otherSet);
                            }
                            break;
                        default:
                            System.out.println("\u001B[31m" + "Invalid type number.");
                            System.out.print("\u001B[34m" + "Input new number -> ");
                            isTypeChoose = true;
                    }
                }

                break;
            } else if (yesNo.equalsIgnoreCase("n")) {
                if (emails.containsKey(EmailType.OTHER)) {
                    emails.get(EmailType.GMAIL).add("----");
                } else {
                    otherSet.add("----");
                    emails.put(EmailType.OTHER, otherSet);
                }
                break;

            } else {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            }
        }
    }
}
