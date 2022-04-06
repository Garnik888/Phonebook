package Validators;


    public class Validators {

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
        /*
        public void deleteForPhoneNumber(PhoneNumberType type, Map<String, Contact> map,
                                         Map<PhoneNumberType, Set<String>> phoneNumbers) {

            String number;

            while (true) {

                System.out.print("Input phone numbers -> ");
                number = in.next();
                while (!Validators.checkPhoneNumber(number)){
                    System.out.println("Incorrect phone number");
                    number=in.next();
                }
                if (!phoneNumbers.get(type).contains(number)) {

                    phoneNumbers.get(type).remove(number);

                    break;
                } else {

                    System.out.println("Incorrect number");
                }

            }

        }
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
        public void add(Map<String, Contact> map) {


    System.out.print("\u001B[34m" + "Input contact's name -> ");
    name = in.next();
    while (name.length() > 20) {
        System.out.print("\u001B[34m" +"Name's max size is 20 characters! Input again ->");
        name = in.next();
    }
    Map<PhoneNumberType, Set<String>> phoneNumbers = new HashMap<>();
    Map<EmailType, Set<String>> emails = new HashMap<>();
*/


