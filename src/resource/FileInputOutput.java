package resource;
import model.Contact;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileInputOutput {

    private final static String FILE = "src/resource/ContactFile";

    public static void writeContact(Map<String, Contact> map) {
        try {
            File fileOne = new File(FILE);
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);


            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception e) {
        }

    }


    public static HashMap<String, Contact> readContacts(Map<String, Contact> mapInFile) {
        try {
            File toRead = new File(FILE);
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);
            mapInFile = (HashMap<String, Contact>) ois.readObject();

            ois.close();
            fis.close();
            //print All data in MAP
             /*   for(Map.Entry<String,Contact> m :mapInFile.entrySet()){
                    System.out.println(m.getKey()+" : "+m.getValue());
                }*/
        } catch (Exception e) {
        }
        return (HashMap<String, Contact>) mapInFile;
    }
}


