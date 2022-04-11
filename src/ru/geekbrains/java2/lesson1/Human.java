package ru.geekbrains.java2.lesson1;

public class Human implements Run, Jump {
    private final String name;
    private boolean pass;


    public Human(String name, boolean pass) {
        this.name = name;
        this.pass = pass;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    @Override
    public int jump() {
        return 1;
    }

    @Override
    public int run() {
        return 10;
    }
}
