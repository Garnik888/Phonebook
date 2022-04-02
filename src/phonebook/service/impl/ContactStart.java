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

        while (true) {

            System.out.print("\u001B[34m" + "\n*****Insert number of operation : -> ");
            int commandNum = in.nextInt();

            switch (commandNum) {

                case 0:

                    System.out.println("\u001B[34m" + "Are you suer to exit Y/N -> ");

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

                    break;
                case 1:

                    System.out.print("\u001B[34m" + "Are you suer to crate contact -> ");

                    while (true) {

                        String yesNo = in.next();

                        if (yesNo.equalsIgnoreCase("y")) {
                            commandService.create(phoneBook);
                            break;
                        } else if (!yesNo.equalsIgnoreCase("n")) {
                            System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
                        } else {

                            break;
                        }
                    }

                    break;
                case 2:

                    System.out.print("\u001B[34m" + "Are you suer to get contact -> ");

                    while (true) {

                        String yesNo = in.next();

                        if (yesNo.equalsIgnoreCase("y")) {
                            commandService.getContact(phoneBook);
                            break;
                        } else if (!yesNo.equalsIgnoreCase("n")) {
                            System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
                        } else {

                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("\u001B[34m" + "Are you suer to update contact -> ");

                    while (true) {

                        String yesNo = in.next();

                        if (yesNo.equalsIgnoreCase("y")) {
                            commandService.update(phoneBook);
                            break;
                        } else if (!yesNo.equalsIgnoreCase("n")) {
                            System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
                        } else {

                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.print("\u001B[34m" + "Are you suer to delet contact -> ");

                    while (true) {

                        String yesNo = in.next();

                        if (yesNo.equalsIgnoreCase("y")) {
                            commandService.delete(phoneBook);
                            break;
                        } else if (!yesNo.equalsIgnoreCase("n")) {
                            System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
                        } else {

                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("\u001B[31m" + "\n****Invalid command number****\n");
            }

            System.out.print("\u001B[34m" + "Insert Y/N if you want view commands list : -> ");
            String yes = in.next();

            while (true) {
                if (yes.equalsIgnoreCase("y")) {

                    CommandService.printCommands();

                    System.out.print("\u001B[34m" + "\n*****Insert number of operation : -> ");
                    commandNum = in.nextInt();
                    break;
                } else if (!yes.equalsIgnoreCase("n")) {

                    System.out.print("\u001B[34m" + "Wrong choose, input Y/N -> ");
                } else {

                    System.out.print("\u001B[34m" + "\n*****Insert number of operation : -> ");
                    commandNum = in.nextInt();
                    break;
                }
            }

            in.close();
        }
    }
}
