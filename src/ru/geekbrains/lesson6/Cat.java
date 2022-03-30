package ru.geekbrains.lesson6;

public class Cat extends Animal {
    private static final int runLength = 200;
    private static final int swimLength = 0;
    private static int countCat;


    public Cat(String nickname, int age, String color) {
        super(nickname, age, color);
        countCat++;
    }

    public int getCatCount() {
        return countCat;
    }

    @Override
    protected void run(int distance) {
        if (distance < 0) {
            return;
        }

        if (distance <= runLength) {
            System.out.println(nickname + " ran " + distance + " m.");
        } else {
            System.out.println("Cat`s distance for run is too long");
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
            System.out.println("Cat`s distance for swim is too long");
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "nickname='" + nickname + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
