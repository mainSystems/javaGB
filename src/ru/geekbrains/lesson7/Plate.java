package ru.geekbrains.lesson7;

public class Plate {
    private int foodQuantity;

    public Plate(int quantity) {
        this.foodQuantity = quantity;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void increaseFood(int quantity) {
        if (quantity >= 0) {
            foodQuantity += quantity;
        }
    }

    public void info() {
        System.out.println("Кол-во корма в миске " + foodQuantity);
    }

    public void decreaseFood(int quantity) {
        if (foodQuantity >= quantity) {
            foodQuantity -= quantity;
        }
    }
}
