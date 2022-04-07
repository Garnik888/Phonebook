package controller;

import model.enums.CommandsEnum;
import model.enums.EmailType;
import model.enums.PhoneNumberType;
import model.enums.UpdateCommandsEnum;

public class ApplicationController {

    /**
     * Print phone type number list
     */
    public static void printPhoneNumbersType() {
        System.out.println("\u001B[34m" + "Choose phone number TYPE \n" + "\n" + "\u001B[32m" + "INPUT "
                + PhoneNumberType.MOBILE.ordinal() + " FOR MOBILE" + "\n" + "INPUT "
                + PhoneNumberType.HOME.ordinal() + " FOR HOME" + "\n" + "INPUT "
                + PhoneNumberType.WORK.ordinal() + " FOR WORK" + "\n" + "INPUT "
                + PhoneNumberType.SCHOOL.ordinal() + " FOR SCHOOL" + "\n" + "INPUT "
                + PhoneNumberType.OTHER.ordinal() + " FOR OTHER" + "\u001B[0m");
    }

    /**
     * Print email type list
     */
    public static void printEmailType() {
        System.out.println("\u001B[34m" + "Choose EMAIL TYPE otherwise press ENTER" + "\n" + "INPUT "
                + EmailType.GMAIL.ordinal() + " FOR GMAIL" + "\n" + "INPUT "
                + EmailType.ICLOUD.ordinal() + " FOR ICLOUD" + "\n" + "INPUT "
                + EmailType.OTHER.ordinal() + " FOR OTHER");
    }

    /**
     * Print update type list
     */
    public static void printUpdateType() {
        System.out.println("\u001B[34m" + "\nChoose update TYPE: \n" + "\n" + "\u001B[32m" + "INPUT "
                + UpdateCommandsEnum.NAME_UPDATE.ordinal() + " FOR update Name" + "\n" + "INPUT "
                + UpdateCommandsEnum.PHONE_NUMBER_UPDATE.ordinal() + " FOR update PhoneNumber" + "\n" + "INPUT "
                + UpdateCommandsEnum.PHONE_NUMBER_DELETE.ordinal() + " FOR delete PhoneNumber" + "\n" + "INPUT "
                + UpdateCommandsEnum.EMAIL_UPDATE.ordinal() + " FOR update Email" + "\n" + "INPUT "
                + UpdateCommandsEnum.COMPANY_NAME_UPDATE.ordinal() + " FOR update company name" + "\u001B[0m"
                + "\n" + "INPUT "
                + UpdateCommandsEnum.COMPANY_NAME_DELETE.ordinal() +
                " FOR delete company name" + "\u001B[0m");
    }

    public static void printCommands() {

        System.out.println("\u001B[32m" + "INPUT "
                + CommandsEnum.CREATE_CONTACT.ordinal() + " FOR CREATE A NEW CONTACT" + "\n" + "INPUT "
                + CommandsEnum.READ_CONTACT.ordinal() + " FOR VIEW ALL CONTACTS" + "\n" + "INPUT "
                + CommandsEnum.UPDATE_CONTACT.ordinal() + " FOR UPDATE CONTACTS" + "\n" + "INPUT "
                + CommandsEnum.DELETE_CONTACT.ordinal() + " FOR DELETE CONTACTS" + "\n" + "INPUT "
                + CommandsEnum.EXIT.ordinal() + " FOR LOGOUT" + "\u001B[0m");
    }
}
