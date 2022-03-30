package phonebook.service;

import phonebook.model.Contact;

import java.util.Map;

public interface Commands {

    void create(Map<String, Contact> map);
    void getContact(Map<String,Contact> map);
    void update(Map<String,Contact> map);
    void delete(Map<String,Contact> map);
}
