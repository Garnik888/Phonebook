package controller.impl;

import controller.ApplicationController;
import model.Contact;
import model.enums.EmailType;
import model.enums.PhoneNumberType;

import java.util.*;

public class EmailControllerImpl {

    Scanner in = new Scanner(System.in);

    /**
     * Build email
     *
     * @param emails Map<EmailType, Set<String>>
     * @param type   EmailType
     * @param email  email
     */
    public void emailMapBuilder(Map<EmailType, Set<String>> emails,
                                EmailType type, String email) {
        if (emails.containsKey(type)) {
            emails.get(type).add(email);

        } else {

            Set<String> emilValues = new HashSet<>();
            emilValues.add(email);
            emails.put(type, emilValues);
        }

    }

    /**
     * Update email
     *
     * @param name String type
     * @param map  Map<String, Contact> map) type
     */
    public void emailUpdate(String name, Map<String, Contact> map,Map<EmailType, Set<String>> emails) {
        ApplicationController.printEmailType();

        String typeNumber;

        boolean isTypeChoose = true;

        while (isTypeChoose) {

            isTypeChoose = false;

            System.out.println("\u001B[34m" + "Choose email type -> ");

            typeNumber = in.next();

            switch (typeNumber) {
                case "0":
                    emailUpdateCase(name, EmailType.GMAIL, map,emails);
                    break;
                case "1":
                    emailUpdateCase(name, EmailType.ICLOUD, map,emails);
                    break;
                case "2":
                    emailUpdateCase(name, EmailType.OTHER, map,emails);
                    break;
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
    public void emailUpdateCase(String name, EmailType type, Map<String, Contact> map,Map<EmailType, Set<String>> emails) {
        System.out.println(map.get(name).getEmails().get(type));
        System.out.print("\u001B[34m" + "Input email which one do you want to update. -> ");
        String email = in.next();

        while (!emails.get(type).contains(email)) {
            System.out.print("\u001B[34m" + "Input right email! -> ");
            email = in.next();
        }
        System.out.print("\u001B[34m" + "Input new email. -> ");
        String newEmail = in.next();
        map.get(name).getEmails().get(type).remove(email);
        map.get(name).getEmails().get(type).add(newEmail);
    }

    /**
     * delete email
     *
     * @param type EmailType type
     * @param map  Map<String, Contact> type
     */
    public void deleteForEmail(EmailType type, Map<String, Contact> map,Map<EmailType, Set<String>> emails) {

        String email;

        while (true) {

            System.out.print("Input email -> ");
            email = in.next();
            if (!emails.get(type).contains(email)) {

                emails.get(type).remove(email);

                break;
            } else {

                System.out.println("Incorrect number");
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


        System.out.print("\u001B[34m" + "Do you want to add email?(Y/N) -> ");

        String yesNo;

        while (true) {
            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("\u001B[34m" + "Input email -> ");
                String email = in.next();

                ApplicationController.printEmailType();
                System.out.print("->");

                boolean isTypeChoose = true;
                String typeNumber;

                while (isTypeChoose) {

                    isTypeChoose = false;
                    typeNumber = in.next();
                    switch (typeNumber) {
                        case "0":
                            if (emails.containsKey(EmailType.GMAIL)){
                                gmailSet.add(email);
                            }else {
                                gmailSet.add(email);
                                emails.put(EmailType.GMAIL, gmailSet);
                            }
                            break;
                        case "1":
                            if (emails.containsKey(EmailType.ICLOUD)){
                                iCloudSet.add(email);
                            }else {
                                iCloudSet.add(email);
                                emails.put(EmailType.ICLOUD, iCloudSet);
                            }
                            break;
                        case "2":
                            if (emails.containsKey(EmailType.OTHER)){
                                otherSet.add(email);
                            }else {
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
            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {
                if (emails.containsKey(EmailType.OTHER)){
                    otherSet.add("---");
                }else {
                    otherSet.add("----");
                    emails.put(EmailType.OTHER, otherSet);
                }
                break;
            }
        }
    }
}
