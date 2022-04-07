package service;

import controller.ContactController;
import controller.impl.ContactControllerImpl;
import model.Contact;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class ContactService implements ContactController {

    String name;
    String company;
    ContactControllerImpl contactController = new ContactControllerImpl();


    @Override
    public void create(Map<String, Contact> map) {

        contactController.add(map,name,company);
    }

    @Override
    public void get(Map<String, Contact> map) {
        contactController.search(map);

    }

    @Override
    public void update(Map<String, Contact> map) {
       contactController.modify(map, company);
    }

    @Override
    public void delete(Map<String, Contact> map) {

        contactController.remove(map);
    }


}
