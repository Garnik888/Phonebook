package phonebook.validators;

public class Validator {

    public static boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        } else if (phoneNumber.startsWith("+")) {
            try {
                Long l = Long.parseLong(phoneNumber.substring(1));
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        } else {
            try {
                Long l = Long.parseLong(phoneNumber);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    }
}
