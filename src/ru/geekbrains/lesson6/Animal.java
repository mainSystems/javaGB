package ru.geekbrains.lesson6;

public abstract class Animal {
    protected String nickname;
    protected int age;
    protected final String color;
    private static int countAnimal;

    public Animal(String nickname, int age, String color) {
        this.nickname = nickname;
        this.age = age;
        this.color = color;
        countAnimal++;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getColor() {
        return color;
    }

    public int getAnimalCount() {
        return countAnimal;
    }

    protected abstract void run(int length);

    protected abstract void swim(int length);


    @Override
    public String toString() {
        return "Animal{" +
                "nickname='" + nickname + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
