package controller.impl;

import controller.ApplicationController;
import model.Contact;
import model.enums.EmailType;
import model.enums.PhoneNumberType;

import java.util.*;

public class EmailControllerImpl {

    Scanner in = new Scanner(System.in);
    Contact contact = new Contact();

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
    public void emailUpdate(String name, Map<String, Contact> map) {
        ApplicationController.printEmailType();

        String typeNumber;

        boolean isTypeChoose = true;

        while (isTypeChoose) {

            isTypeChoose = false;

            System.out.println("\u001B[34m" + "Choose email type -> ");

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

        String email;

        while (true) {

            System.out.print("Input email -> ");
            email = in.next();
            if (!contact.getEmails().get(type).contains(email)) {

                contact.getEmails().get(type).remove(email);

                break;
            } else {

                System.out.println("Incorrect number");
            }

        }
    }

    /**
     * Map email editor
     */
    public Map<EmailType, Set<String>> createEmailSet() {

        Map<EmailType, Set<String>> emailMaps = new HashMap<>();
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
                            emailMapBuilder(emailMaps, EmailType.GMAIL, email);
                            break;
                        case "1":
                            emailMapBuilder(emailMaps, EmailType.ICLOUD, email);
                            break;
                        case "2":
                            emailMapBuilder(emailMaps, EmailType.OTHER, email);
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

                emailMapBuilder(emailMaps, EmailType.OTHER,
                        "--------");

                break;
            }
        }

        return emailMaps;
    }
}
