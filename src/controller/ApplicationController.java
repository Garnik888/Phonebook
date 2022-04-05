package controller;

import model.enums.CommandsEnum;
import model.enums.EmailType;
import model.enums.PhoneNumberType;
import model.enums.UpdateCommandsEnum;

public class ApplicationController {

    /**
     * Print phone type number list
     */
    public static  void printPhoneNumbersType() {
        System.out.println("\u001B[34m" + "Choose phone number TYPE \n");

        System.out.println("\u001B[32m" + "INPUT " + PhoneNumberType.MOBILE.ordinal() + " FOR MOBILE");
        System.out.println("INPUT " + PhoneNumberType.HOME.ordinal() + " FOR HOME");
        System.out.println("INPUT " + PhoneNumberType.WORK.ordinal() + " FOR WORK");
        System.out.println("INPUT " + PhoneNumberType.SCHOOL.ordinal() + " FOR SCHOOL");
        System.out.print("INPUT " + PhoneNumberType.OTHER.ordinal() + " FOR OTHER" + "\u001B[0m");
    }

    /**
     * Print email type list
     */
    public static void printEmailType() {
        System.out.println("\"\\u001B[34m\"Choose EMAIL TYPE otherwise press ENTER\n");

        System.out.println("INPUT " + EmailType.GMAIL.ordinal() + " FOR GMAIL");
        System.out.println("INPUT " + EmailType.ICLOUD.ordinal() + " FOR ICLOUD");
        System.out.println("INPUT " + EmailType.OTHER.ordinal() + " FOR OTHER");
    }

    /**
     * Print update type list
     */
    public static  void printUpdateType() {
        System.out.println("\u001B[34m" + "\nChoose update TYPE: \n");

        System.out.println("\u001B[32m" + "INPUT " + UpdateCommandsEnum.NAME_UPDATE.ordinal() + " FOR update Name");
        System.out.println("INPUT " + UpdateCommandsEnum.PHONE_NUMBER_UPDATE.ordinal() + " FOR update PhoneNumber");
        System.out.println("INPUT " + UpdateCommandsEnum.PHONE_NUMBER_UPDATE.ordinal() + " FOR delete PhoneNumber");
        System.out.println("INPUT " + UpdateCommandsEnum.EMAIL_UPDATE.ordinal() + " FOR update Email");
        System.out.println("INPUT " + UpdateCommandsEnum.EMAIL_UPDATE.ordinal() + " FOR delete Email");
        System.out.println("INPUT " + UpdateCommandsEnum.COMPANY_NAME_UPDATE.ordinal() +
                " FOR update company name" + "\u001B[0m");
        System.out.println("INPUT " + UpdateCommandsEnum.COMPANY_NAME_UPDATE.ordinal() +
                " FOR delete company name" + "\u001B[0m");
    }

    public static void printCommands() {

        System.out.println("\u001B[32m" + "INPUT " + CommandsEnum.CREATE_CONTACT.ordinal() + " FOR CREATE A NEW CONTACT");
        System.out.println("INPUT " + CommandsEnum.READ_CONTACT.ordinal() + " FOR VIEW ALL CONTACTS");
        System.out.println("INPUT " + CommandsEnum.UPDATE_CONTACT.ordinal() + " FOR UPDATE CONTACTS");
        System.out.println("INPUT " + CommandsEnum.DELETE_CONTACT.ordinal() + " FOR DELETE CONTACTS");
        System.out.println("INPUT " + CommandsEnum.EXIT.ordinal() + " FOR LOGOUT" + "\u001B[0m");
    }
}
