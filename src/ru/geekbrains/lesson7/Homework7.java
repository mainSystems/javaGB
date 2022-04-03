package ru.geekbrains.lesson7;

public class Homework7 {
    public static void main(String[] args) {
        Cat[] catArray = new Cat[2];
        Plate plate = new Plate(10);
        catArray[0] = new Cat("Mikrob", 6, false);
        catArray[1] = new Cat("Kuzya",8, false);

        plate.info();
        System.out.println("--------------------------------");
        for (Cat cat : catArray) {
            cat.eat(plate);
        }
        System.out.println("--------------------------------");
        plate.info();

        plate.increaseFood(30);
        plate.info();
        plate.increaseFood(-1);
        plate.info();
    }
}
