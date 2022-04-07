package controller;

import model.Contact;

import java.util.HashMap;
import java.util.Map;

public interface ContactController {
    void create(Map<String, Contact> map);
    void get(Map<String,Contact> map);
    void update(Map<String,Contact> map);
    void delete(Map<String,Contact> map);
}
