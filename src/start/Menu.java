package start;

import controller.ApplicationController;
import model.Contact;
import service.ContactService;

import java.io.*;
import java.util.*;

public class Menu implements Serializable {


    private final Scanner in = new Scanner(System.in);

    HashMap<String, Contact> phoneBook = new HashMap<>();


    ContactService contactService = new ContactService();

    public void start() {

       phoneBook= readContacts(phoneBook);

        ApplicationController.printCommands();

        String commandNum;

        while (true) {

            System.out.print("\u001B[34m" + "\n*****Insert number of operation : -> ");
            commandNum = in.next();

            switch (commandNum) {

                case "0":
                   writeContact(phoneBook);
                    exit();
                    break;
                case "1":
                    create();
                    break;
                case "2":
                    get();

                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
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


    public void exit() {
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

    }


    public void create() {
        System.out.print("\u001B[34m" + "Are you sure to crate contact(Y/N) -> ");

        String yesNo;

        while (true) {

            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.create(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

    }


    public void get() {
        System.out.print("\u001B[34m" + "Are you sure to get contact(Y/N) -> ");

        String yesNo;

        while (true) {

            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.get(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

    }

    public void update() {
        System.out.print("\u001B[34m" + "Are you sure to update contact(Y/N) -> ");

        String yesNo;

        while (true) {

            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.update(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }

    }

    public void delete() {
        System.out.print("\u001B[34m" + "Are you sure to delete contact(Y/N) -> ");

        String yesNo;

        while (true) {

            yesNo = in.next();

            if (yesNo.equalsIgnoreCase("y")) {
                contactService.delete(phoneBook);
                break;
            } else if (!yesNo.equalsIgnoreCase("n")) {
                System.out.println("\u001B[31m" + "Wrong choose, input Y/N");
            } else {

                break;
            }
        }
    }

    public static void writeContact(HashMap<String, Contact> map) {
        try {
            File fileOne=new File("fileone");
            FileOutputStream fos=new FileOutputStream(fileOne);
            ObjectOutputStream oos=new ObjectOutputStream(fos);


            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Exception e) {}

    }




        public static HashMap<String,Contact> readContacts(Map<String, Contact> mapInFile) {
            try {
                File toRead=new File("fileone");
                FileInputStream fis=new FileInputStream(toRead);
                ObjectInputStream ois=new ObjectInputStream(fis);
                mapInFile = (HashMap<String, Contact>) ois.readObject();

                ois.close();
                fis.close();
                //print All data in MAP
             /*   for(Map.Entry<String,Contact> m :mapInFile.entrySet()){
                    System.out.println(m.getKey()+" : "+m.getValue());
                }*/
            } catch(Exception e) {}
            return (HashMap<String, Contact>) mapInFile;
        }


    /*

    public void write(Map<String, Contact> mapRead) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("people.bin"))) {
            oos.writeObject(phoneBook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(Map<String, Contact> mapRead) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("people.bin"))) {
                System.out.println(Arrays.toString(new Map[]{phoneBook}));
            } catch (IOException e) {
                e.printStackTrace();
            }}




*/


/*
    public static void writeContacts(Map<String, Contact> map) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\User\\IdeaProjects\\Phonebook9999\\src\\resource\\ContactFile");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(map);
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

   public static void readContact(Map<String, Contact> map) {

        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\User\\IdeaProjects\\Phonebook9999\\src\\resource\\ContactFile");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            map.( objectInputStream.readObject());

            System.out.println(map);
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("FILE NOT FOUND");
        }
    }

*/
/*
    public void write(Map<String, Contact> mapRead) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Contact.txt"));
            writer.write(mapRead.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Contact> read(Map<String, Contact> mapRead) {
        try {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader("Contact.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String[] str;
            String line;
            while (true) {
                try {
                    try {
                        if ((line = reader.readLine()) != null) {
                            str = line.split("%");
                            Map<String, String> l = new HashMap<>();
                            l.put(str[0], str[1]);
                            phoneBook.g
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }*/
}
