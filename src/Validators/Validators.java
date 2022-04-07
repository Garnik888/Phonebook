package Validators;


public class Validators {

    public static boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+?[0-9 ]{6,13}$");
    }

    public static boolean checkNameSize(String name) {
        return name.length() < 20;
    }

    public static boolean checkEmail(String email) {
        if (email == null) {
            return false;
        } else return email.contains("@") && email.length() < 20 && email.length() > 6;
    }

}

