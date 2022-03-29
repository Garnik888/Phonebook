package phonebook.service;

import phonebook.model.Contact;

import java.util.*;

public class ContactStart {

    //Class field
    private String name;
    private Scanner in = new Scanner(System.in);

    public void start() {

        Map<String, Contact> phoneBook = new HashMap<>();
        ContactService contactService = new ContactService();

        ContactService.printCommands();
        System.out.print("\n*****Insert number of operation : -> ");
        int commandNum = in.nextInt();

        while (true) {

            switch (commandNum) {

                case 0:

                    System.exit(0);
                case 1:

                    contactService.create();
                    break;
                case 2:

                    contactService.getContact();
                    break;
                case 3:
                    contactService.update();
                    break;
                case 4:
                    contactService.delete();
                    break;
                default:
                    System.out.println("\n****Invalid command number****\n");
            }

            System.out.print("Insert YES/NO if you want view commands list : -> ");

            String yes = in.next();
            if (yes.equalsIgnoreCase("yes")) {

                ContactService.printCommands();
            }

            System.out.print("\n*****Insert number of operation : -> ");
            commandNum = in.nextInt();
        }
    }

}
