import controller.ApplicationController;
import model.Contact;
import service.ContactService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {


    private String name;
    private final Scanner in = new Scanner(System.in);

    Map<String, Contact> phoneBook = new HashMap<>();
    ContactService contactService = new ContactService();

    public void start() {


        ApplicationController.printCommands();

        while (true) {

            System.out.print("\u001B[34m" + "\n*****Insert number of operation : -> ");
            int commandNum;

            commandNum = in.nextInt();


            switch (commandNum) {

                case 0:
                    exit();
                    break;
                case 1:
                    create();
                    break;
                case 2:
                    get();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                default:
                    System.out.println("\u001B[31m" + "\n****Invalid command number****\n");
            }


            System.out.print("\u001B[34m" + "\nInsert Y/N if you want view commands list : -> ");

            while (true) {

                String yes = in.next();

                if (yes.equalsIgnoreCase("y")) {

                    ApplicationController.printCommands();

                } else if (!yes.equalsIgnoreCase("n")) {

                    System.out.print("\u001B[34m" + "Wrong choose, input Y/N -> ");
                } else {

                    break;
                }
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


    public void create() {
        System.out.print("\u001B[34m" + "Are you sure to crate contact(Y/N) -> ");

        while (true) {

            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.create(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

    }


    public void get() {
        System.out.print("\u001B[34m" + "Are you sure to get contact(Y/N) -> ");

        while (true) {

            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.get(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

    }

    public void update() {
        System.out.print("\u001B[34m" + "Are you sure to update contact(Y/N) -> ");

        while (true) {

            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.update(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

    }

    public void delete() {
        System.out.print("\u001B[34m" + "Are you sure to delete contact(Y/N) -> ");

        while (true) {

            String yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.delete(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }
    }


}
