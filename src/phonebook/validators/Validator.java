package phonebook.validators;

public class Validator {

    public static boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        } else if (phoneNumber.startsWith("+")) {
            try {
                Long l = Long.parseLong(phoneNumber.substring(1));
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect phone number");
                return false;
            }
            return true;
        } else {
            try {
                Long l = Long.parseLong(phoneNumber);
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect phone number");
                return false;
            }
            return true;
        }
    }

    public static boolean checkNameSize(String name) {
        return name.length() < 20;
    }

    public static boolean checkEmailOtherContains(String email) {
        if (email == null) {
            return false;
        } else return email.contains("@");
    }

    public static boolean checkEmailGmail(String email) {
        if (email == null) {
            return false;
        } else return email.toLowerCase().endsWith("@gmail.com");
    }

    public static boolean checkEmailIcloud(String email) {
        if (email == null) {
            return false;
        } else return email.toLowerCase().endsWith("@icloud.com");
    }
}
