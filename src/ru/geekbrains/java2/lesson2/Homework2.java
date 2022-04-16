package ru.geekbrains.java2.lesson2;

import java.util.Arrays;
import java.util.Random;

public class Homework2 {
    public static final int ARRAY_SIZE = 5;
    public static final int ARRAY_CORRECT_VALUE = 4;
    public static final String errorArraySize = "wrong number of field must be: " + ARRAY_CORRECT_VALUE + "x" + ARRAY_CORRECT_VALUE;
    public static final String errorArrayFiledData = "wrong data of field ";
    public static final String[][] arrTest = new String[][]{
            {"the", "2", "3", "4"},
            {"1", "2", "3", "4"},
            {"1", "2", "3", "4"},
            {"1", "2", "3", "4"}
    };

    public static void main(String[] args) {
        String[][] array = new String[ARRAY_SIZE][ARRAY_SIZE];
        genArray(array);

        tryArray(array);
        tryArray(arrTest);
    }

    private static void genArray(String[][] array) {
        Random rnd = new Random();

        for (String[] strings : array) {
            int arrVal = rnd.nextInt(8);
            Arrays.fill(strings, String.valueOf(arrVal));
        }
    }

    private static int sumArray(String[][] array) {
        checkArraySize(array);
        int sum = 0;

        try {
            for (int x = 0; x < ARRAY_SIZE; x++) {
                for (int y = 0; y < ARRAY_SIZE; y++) {
                    String val = array[x][y];
                    try {
                        sum += Integer.parseInt(val);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException(errorArrayFiledData + " at " + "[" + x + "]" + "[" + y + "]" + " and contain: " + val);
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MyArrayIndexOutOfBounds("Wrong number of field in for. \n Check ARRAY_SIZE AND ARRAY_CORRECT_VALUE\n tips: replace ARRAY_SIZE to ARRAY_CORRECT_VALUE\n");
        }
        return sum;
    }

    private static void checkArraySize(String[][] array) {
        if (array.length != ARRAY_CORRECT_VALUE) {
            throw new MyArraySizeException(errorArraySize);
        }

        for (String[] row : array) {
            if (row.length != ARRAY_CORRECT_VALUE) {
                throw new MyArraySizeException(errorArraySize);
            }
        }
    }

    private static void tryArray(String[][] array) {
        try {
            System.out.println(sumArray(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Arrays.deepToString(array));
    }
}
