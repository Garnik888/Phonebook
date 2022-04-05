package controller.impl;

import controller.ApplicationController;
import model.Contact;
import model.enums.EmailType;

import java.util.*;

public class EmailControllerImpl {

    Scanner in = new Scanner(System.in);
    Contact contact = new Contact();

    /**
     * Map email editor
     */
    public void createEmailSet() {

        System.out.print("\u001B[34m" + "Do you want to add email?(Y/N) -> ");
        Set<String> emailSet = new HashSet<>();

        String yesNo;

        while (true) {
            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {

                System.out.print("\u001B[34m" + "Input email -> ");
                String email = in.next();

                emailSet.add(email);

                ApplicationController.printEmailType();
                System.out.print("->");

                boolean isTypeChoose = true;
                String typeNumber;

                while (isTypeChoose) {

                    isTypeChoose = false;
                    typeNumber = in.next();
                    switch (typeNumber) {
                        case "0":
                            emailMapBuilder(EmailType.GMAIL, emailSet, email);
                            break;
                        case "1":
                            emailMapBuilder(EmailType.ICLOUD, emailSet, email);
                            break;
                        case "2":
                            emailMapBuilder(EmailType.OTHER, emailSet, email);
                            break;
                        default:
                            System.out.println("\u001B[31m" + "Invalid type number.");
                            System.out.print("\u001B[34m" + "Input new number -> ");
                            isTypeChoose = true;
                    }
                }

            } else if (!yesNo.equalsIgnoreCase("n")) {

                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
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
    public void emailMapBuilder(EmailType type, Set<String> emailSet,
                                String email) {
        if (!contact.getEmails().containsKey(type)) {
            contact.getEmails().put(type, emailSet);
        } else {

            contact.getEmails().get(type).add(email);
        }

    }

    /**
     * Update email
     *
     * @param name String type
     * @param map  Map<String, Contact> map) type
     */
    public void emailUpdate(String name, Map<String, Contact> map) {
        ApplicationController.printEmailType();

        while (true) {
            System.out.println("\u001B[34m" + "Choose email type -> ");
            String typeNumber;


            typeNumber = in.next();
            switch (typeNumber) {
                case "0":
                    emailUpdateCase(name, EmailType.GMAIL, map);
                    break;
                case "1":
                    emailUpdateCase(name, EmailType.ICLOUD, map);
                    break;
                case "2":
                    emailUpdateCase(name, EmailType.OTHER, map);
                    break;
                default:
                    System.out.println("\u001B[31m" + "Invalid type number.");
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

        while (!contact.getEmails().get(type).contains(email)) {
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
    public void deleteForEmail(EmailType type, Map<String, Contact> map) {
        while (true) {

            System.out.print("Input email -> ");
            String email = in.next();
            if (!contact.getEmails().get(type).contains(email)) {

                contact.getEmails().get(type).remove(email);

                break;
            } else {

                System.out.println("Incorrect number");
            }

        }
    }

}
