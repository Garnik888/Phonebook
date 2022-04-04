package phonebook.service.impl;

import org.w3c.dom.ls.LSOutput;
import phonebook.model.Contact;

import java.util.*;

public class ContactStart {

    //Class field
    private String name;
    private final Scanner in = new Scanner(System.in);


    public void start() {


        Map<String, Contact> phoneBook = new HashMap<>();
        CommandService commandService = new CommandService();

        CommandService.printCommands();

        while (true) {

            System.out.print("\u001B[34m" + "\n*****Insert number of operation : -> ");
            int commandNum;
            try {
                commandNum = in.nextInt();


                switch (commandNum) {

                    case 0:

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

                        break;
                    case 1:

                        System.out.print("\u001B[34m" + "Are you sure to crate contact -> ");

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

                        System.out.print("\u001B[34m" + "Are you sure to get contact -> ");

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
                        System.out.print("\u001B[34m" + "Are you sure to update contact -> ");

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
                        System.out.print("\u001B[34m" + "Are you sure to delete contact -> ");

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

            } catch (InputMismatchException ime) {

                System.out.println("input number from 0 to 4");
            }


            System.out.print("\u001B[34m" + "\nInsert Y/N if you want view commands list : -> ");

            while (true) {

                String yes = in.next();

                if (yes.equalsIgnoreCase("y")) {

                    CommandService.printCommands();

                } else if (!yes.equalsIgnoreCase("n")) {

                    System.out.print("\u001B[34m" + "Wrong choose, input Y/N -> ");
                } else {

                    break;
                }
            }
        }
    }
}
