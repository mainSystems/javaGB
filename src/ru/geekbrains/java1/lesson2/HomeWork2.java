package ru.geekbrains.java1.lesson2;

public class HomeWork2 {

    public static void main(String[] args) {
        int x = -1;
        int y = 6;
        int year = 2016;
        String str = "Task done!";

        System.out.println(checkSum(x, y));
        isPositiveOrNegative(x);
        isPositiveOrNegative2(y);
        printString(y,str);
        System.out.println(leapYear(year));
    }
//Task 1
    public static boolean checkSum(int a, int b) {
        return ((a + b) >= 10 && (a + b) <= 20) ? true : false;
    }
//Task2
    public static void isPositiveOrNegative(int x){
        String check = ((x > 0 || x==0 )?"positive" : "negative");
        System.out.println(check);
    }
//Task 3
    public static boolean isPositiveOrNegative2(int x){
        return ((x > 0 || x==0 )? true : false);
    };
//Task 4
    public static void printString(int x, String str){
        if (x>0){
            for (int i = 0; i < x; i++) {
                System.out.println(str);
            }
        }
    }
//Task 5
    public static boolean leapYear (int year) {
        if ((( year % 4 == 0 ) && ( year % 100 != 0 )) || ( year % 400 == 0 )) { return true; }
        else { return false; }
    }
}