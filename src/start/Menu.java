package start;

import controller.ApplicationController;
import model.Contact;
import model.enums.PhoneNumberType;
import resource.FileInputOutput;
import service.ContactService;

import java.io.*;
import java.util.*;

public class Menu implements Serializable {
    ContactService contactService = new ContactService();
    ApplicationAnswers applicationAnswers=new ApplicationAnswers();
    private final Scanner in = new Scanner(System.in);


    Map<String, Contact> phoneBook = new HashMap<>();


    public void start() {

        phoneBook = FileInputOutput.readContacts(phoneBook);

        ApplicationController.printCommands();

        String commandNum;

        while (true) {

            System.out.print("\u001B[34m" + "\n*****Insert number of operation : -> ");
            commandNum = in.next();

            switch (commandNum) {

                case "0":
                    FileInputOutput.writeContact(phoneBook);
                    applicationAnswers.exit();
                    break;
                case "1":
                    applicationAnswers.create(phoneBook);
                    break;
                case "2":
                    applicationAnswers.get(phoneBook);
                    break;
                case "3":
                    applicationAnswers.update(phoneBook);
                    break;
                case "4":
                    applicationAnswers.delete(phoneBook);

                    break;
                default:
                    System.out.println("\u001B[31m" + "\n****Invalid command number****\n");
            }


            System.out.print("\u001B[34m" + "\nInsert Y/N if you want view commands list : -> ");

            String yesNo;

            while (true) {

                yesNo = in.next();

                if (yesNo.equalsIgnoreCase("y")) {

                    ApplicationController.printCommands();
                    break;
                } else if (!yesNo.equalsIgnoreCase("n")) {

                    System.out.print("\u001B[34m" + "Wrong choose, input Y/N -> ");
                } else {

                    break;
                }
            }


        }
    }


}