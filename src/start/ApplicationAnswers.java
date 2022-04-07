package start;

import model.Contact;
import service.ContactService;

import java.util.*;

public class ApplicationAnswers {
    ContactService contactService = new ContactService();
    Scanner in = new Scanner(System.in);

    public void create(Map<String, Contact> phoneBook) {
        System.out.print("\u001B[34m" + "Are you sure to crate contact(Y/N) -> ");

        String yesNo;

        while (true) {

            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.create(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
                break;
            } else {

                break;
            }
        }

    }


    public void get(Map<String, Contact> phoneBook) {
        System.out.print("\u001B[34m" + "Are you sure to get contact(Y/N) -> ");

        String yesNo;

        while (true) {

            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.get(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
                break;
            } else {

                break;
            }
        }

    }

    public void update(Map<String, Contact> phoneBook) {
        System.out.print("\u001B[34m" + "Are you sure to update contact(Y/N) -> ");

        String yesNo;

        while (true) {

            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.update(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
                break;
            } else {

                break;
            }
        }

    }

    public void delete(Map<String, Contact> phoneBook) {
        System.out.print("\u001B[34m" + "Are you sure to delete contact(Y/N) -> ");

        String yesNo;

        while (true) {

            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.delete(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
                break;
            } else {

                break;
            }
        }
    }


    public void exit() {
        System.out.println("\u001B[34m" + "Are you sure to exit Y/N ->  ");

        while (true) {

            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                System.exit(0);
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

    }

}

