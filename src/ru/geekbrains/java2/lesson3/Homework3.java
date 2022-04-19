package ru.geekbrains.java2.lesson3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Homework3 {
    public static void main(String[] args) {
        String[] words = new String[]{
                "Cat",
                "Dog",
                "Eat",
                "Cat",
                "Dog",
                "Sleep",
                "Modem",
                "Coaxial",
                "Cat",
                "Cat",
                "Word",
                "Moment"
        };
        System.out.println(Arrays.toString(words));


        HashSet<String> wordsHS = new HashSet<String>(Arrays.asList(words));
        System.out.println(wordsHS);

        HashMap<String, Integer> wordsHM = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            Integer count = 1;
            String s1 = words[i];
            if (!wordsHM.containsKey(s1)) {
                wordsHM.put(s1, count);
            }
            for (int j = 0; j < words.length; j++) {

                if (i == j) {
                    continue;
                }
                String s2 = words[j];

                if (s1.equals(s2)) {
                    count++;
                    wordsHM.put(s1, count);
                }
            }

        }
        System.out.println(wordsHM);
        //---------------------------Task 2------------------------------
        Phonebook pb = new Phonebook();
        pb.add("DnMing","89217744084");
        pb.add("DnMing","89110801315");
        pb.add("Yulia","89110027387");
        pb.add("112","112");
        pb.add("112","911");

        System.out.println("PhoneBook:");
        for (String name: pb.phoneBook.keySet()) {
            System.out.println("------------------------------------");
            pb.get(name);
        }
    }
}
