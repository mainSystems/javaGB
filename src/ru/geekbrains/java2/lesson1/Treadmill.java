package ru.geekbrains.java2.lesson1;

public class Treadmill {
    private final int length;

    public Treadmill(int length) {
        this.length = length;
    }

    public boolean canRun(Run runSomeone) {
        int runSomeoneLength = runSomeone.run();
        return runSomeoneLength >= this.length;
    }

    @Override
    public String toString() {
        return "Treadmill{" +
                "length=" + length +
                '}';
    }
}
