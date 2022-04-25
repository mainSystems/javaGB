package ru.geekbrains.java2.lesson5;

import java.util.Arrays;

public class Homework5 {
    public static final int SIZE = 10_000_000;
    public static final float VALUE = 1.0f;
    public static final float[] ARR = new float[SIZE];

    public static void main(String[] args) {
        fillArray(ARR, VALUE);
        firstMethod();
        secondMethod();
    }


    public static void firstMethod() {
        long startTime = System.currentTimeMillis();

        calculateArray(ARR);
        System.out.printf("One thread time: %d ms.%n", System.currentTimeMillis() - startTime);
    }

    public static void secondMethod() {
        long startTime = System.currentTimeMillis();

        splitMergeCalculateArray(ARR);

        System.out.printf("Two thread time: %d ms.%n", System.currentTimeMillis() - startTime);
    }

    public static void fillArray(float[] arr, float value) {
        Arrays.fill(arr, value);
    }

    public static void calculateArray(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void splitMergeCalculateArray(float[] initialArray) {

        float[] leftHalf = new float[SIZE / 2];
        float[] rightHalf = new float[SIZE / 2];
        float[] mergedArray = new float[SIZE];

        System.arraycopy(initialArray, 0, leftHalf, 0, SIZE / 2);
        System.arraycopy(initialArray, SIZE / 2, rightHalf, 0, SIZE / 2);

        Thread threadCalculate1 = new Thread(() -> calculateArray(leftHalf));
        Thread threadCalculate2 = new Thread(() -> calculateArray(rightHalf));

        threadCalculate1.start();
        threadCalculate2.start();
        

        System.arraycopy(leftHalf, 0, mergedArray, 0, SIZE / 2);
        System.arraycopy(rightHalf, 0, mergedArray, SIZE / 2, SIZE / 2);

        //System.out.println(Arrays.toString(mergedArray));
    }

}


