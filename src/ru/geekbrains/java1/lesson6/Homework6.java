package ru.geekbrains.java1.lesson6;

public class Homework6 {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Kuzya", 3, "black-white");
        cat1.run(-1);
        cat1.swim(1);
        Cat cat2 = new Cat("Mikrob", 3, "black-white");
        System.out.println("Count of Cat: " + cat1.getCatCount());

        Dog dog1 = new Dog("Ball", 8, "white");
        dog1.run(350);
        dog1.swim(10);
        System.out.println("Count of Dog: " + dog1.getDogCount());

        System.out.println("Count of Dog: " + dog1.getDogCount());

        Animal animals = (Animal) cat1;
        System.out.println("Count of animals: " + animals.getAnimalCount());
    }
}
