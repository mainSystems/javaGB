package ru.geekbrains.lesson7;

public class Cat {

    private final String name;
    private final int appetite;
    private boolean satiety;

    public Cat(String name, int appetite, boolean satiety) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = satiety;
    }

    public void eat(Plate plate) {
        int foodQuantityStart = plate.getFoodQuantity();
        System.out.print("Кот " + name + " подошёл к миске");
        plate.decreaseFood(appetite);
        if (plate.getFoodQuantity() != foodQuantityStart) {
            System.out.println(", съел " + appetite + " корма.");
            satiety = true;
        } else {
            System.out.println("");
            System.out.print("Но корма не хватило, и он остался ");
        }
        if (satiety) {
            System.out.println("Кот " + name + " сыт");
        } else {
            System.out.println("голоден");
        }
    }

}