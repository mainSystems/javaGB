package ru.geekbrains.java1.lesson3;

import java.util.Arrays;
import java.util.Random;

public class HomeWork3 {
    public static void main(String[] args) {
        int len = 100;
        int initialValue = 8;
        int[] checkBalance = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] checkBalance2 = {2, 2, 2, 1, 2, 2, 14, 1};


        reverseArray(len);
        fillArray(len);
        multiplyArray();
        fillDiagonalArray(initialValue);
        System.out.println(Arrays.toString(returnArray(len, initialValue)));
        findMinMaxInArray(len, initialValue);
        System.out.println(checkArraySides(checkBalance));
        shiftArray(checkBalance2, initialValue);
    }

    //Task1
    public static void reverseArray(int size) {
        if (size <= 0) { return; }
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            int rnd = random.nextInt(2);
            array[i] = rnd;
        }
        System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length; i++) {
            switch (array[i]) {
                case 1:
                    array[i] = 0;
                    break;
                default:
                    array[i] = 1;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    //Task2
    public static void fillArray(int size) {
        if (size <= 0) { return; }
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(Arrays.toString(array));
    }

    //Task3
    public static void multiplyArray() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    //Task4
    public static void fillDiagonalArray(int size) {
        if (size <= 0) { return; }
        int[][] array = new int[size][size];

        for (int i = 0, j = array.length - 1; i < array.length; i++, j--) {
            array[i][i] = 1;
            array[i][j] = 1;
        }

        System.out.println(Arrays.deepToString(array));
    }

    //Task5
    public static int[] returnArray(int size, int val) {
        if (size <= 0) { int[] array = {0}; } //maybe try-catch?
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = val;
        }
        return array;
    }

    //Task6
    public static void findMinMaxInArray(int size, int val) {
        if (size <= 0) { return; }
        Random random = new Random();
        int[] array = new int[size];
        int minNum = array[0];
        int maxNum = array[0];

        for (int i = 0; i < array.length; i++) {
            int rnd = random.nextInt(val);
            array[i] = rnd;
        }
        System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxNum) {
                maxNum = array[i];
            } else if (array[i] < minNum) {
                minNum = array[i];
            }
        }
        System.out.printf("min: %d, max: %d\n", minNum, maxNum);
    }

    //Task7
    public static boolean checkArraySides(int arr[]) {
        int sumArrElements = 0, sumTemp = 0;
        for (int i = 0; i < arr.length; i++) {
            sumArrElements += arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            sumTemp += arr[i];
            if (sumArrElements / sumTemp == 2 && sumArrElements % sumTemp == 0) {
                return true;
            }
        }
        return false;
    }

    //Task8
    public static void shiftArray(int arr[], int shiftVal) {
        if (shiftVal == 0 || arr.length == 1) {
            return;
        }

        if (shiftVal < 0) {
            while (shiftVal < 0) {
                int lastElement = arr[arr.length - 1];
                for (int i = arr.length - 1; i > 0; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[0] = lastElement;
                shiftVal++;
            }
        } else {
            while (shiftVal > 0) {
                int firstElement = arr[0];
                for (int i = 0; i < arr.length - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = firstElement;
                shiftVal--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
