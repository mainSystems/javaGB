package ru.geekbrains.java2.lesson1;

public class Cat implements Run, Jump {
    private final String name;
    private final Color color;
    private boolean pass;

    public Cat(String name, Color color, boolean pass) {
        this.name = name;
        this.color = color;
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
        return 2;
    }

    @Override
    public int run() {
        return 200;
    }
}
