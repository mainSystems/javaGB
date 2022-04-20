package ru.geekbrains.java2.lesson3;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Phonebook {
    TreeMap<String, Set<String>> phoneBook = new TreeMap<>();

    public void add(String surname, String phone) {
        Set<String> phones = phoneBook.get(surname);

        if (!phoneBook.containsKey(surname)) {
            phones = new HashSet<>();
        }
       // (!phoneBook.containsKey(surname)) ? (phones = new HashSet<>());
        phones.add(phone);
        phoneBook.put(surname, phones);
    }

    public void get(String surname) {
        String out = (phoneBook.containsKey(surname)) ? (surname + " = " + phoneBook.get(surname)) : ("No numbers for: " + surname);
        System.out.println(out);
    }


}
