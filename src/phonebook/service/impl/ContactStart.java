package phonebook.service.impl;

import org.w3c.dom.ls.LSOutput;
import phonebook.model.Contact;

import java.util.*;

public class ContactStart {

    //Class field
    private String name;
    private Scanner in = new Scanner(System.in);


    public void start() {


        Map<String, Contact> phoneBook = new HashMap<>();
        CommandService commandService = new CommandService();

        CommandService.printCommands();
        System.out.print("\n*****Insert number of operation : -> ");
        int commandNum = in.nextInt();

        while (true) {

            switch (commandNum) {

                case 0:

                    System.exit(0);
                case 1:

                    commandService.create(phoneBook);
                    break;
                case 2:

                    commandService.getContact(phoneBook);
                    break;
                case 3:
                    commandService.update(phoneBook);
                    break;
                case 4:
                    commandService.delete(phoneBook);
                    break;
                default:
                    System.out.println("\n****Invalid command number****\n");
            }

            System.out.print("Insert YES/NO if you want view commands list : -> ");
            String yes = in.next();

            if (yes.equalsIgnoreCase("yes")) {

                CommandService.printCommands();
            }

            System.out.print("\n*****Insert number of operation : -> ");
            commandNum = in.nextInt();



        }
    }

}
