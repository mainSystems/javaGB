package ru.geekbrains.java2.lesson1;

public class Wall {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    public boolean canJump(Jump jumpSomeone) {
        int jumpSomeoneHeight = jumpSomeone.jump();
        return jumpSomeoneHeight >= this.height;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "height=" + height +
                '}';
    }
}
