package ru.geekbrains.java1.lesson6;

public class Dog extends Animal {
    private static final int runLength = 500;
    private static final int swimLength = 10;
    private static int countDog;


    public Dog(String nickname, int age, String color) {
        super(nickname, age, color);
        countDog++;
    }

    public static int getDogCount() {
        return countDog;
    }

    @Override
    protected void run(int distance) {
        if (distance < 0) {
            return;
        }

        if (distance <= runLength) {
            System.out.println(nickname + " ran " + distance + " m.");
        } else {
            System.out.println("Dog`s distance for ran is too long");
        }
    }

    @Override
    protected void swim(int distance) {
        if (distance < 0) {
            return;
        }

        if (distance <= swimLength) {
            System.out.println(nickname + " swam " + distance + " m.");
        } else {
            System.out.println("Dog`s distance for swim is too long");
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "nickname='" + nickname + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
