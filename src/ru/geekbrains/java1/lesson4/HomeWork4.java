package ru.geekbrains.java1.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final int DOTS_TO_WIN = 4;
    public static final int SIZE = 5;
    public static char[][] field;
    public static char[][] fieldProbablyWin;
    public static String result;
    public static int counterDot = 0;
    public static int checkStatus;

    public static void main(String[] args) {
        initGameField();
        startGame();
    }

    public static void initGameField() {
        field = new char[SIZE][SIZE];
        fieldProbablyWin = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(field[i], DOT_EMPTY);
            Arrays.fill(fieldProbablyWin[i], DOT_EMPTY);
        }
    }

    public static void startGame() {
        while (isChecksSuccess() == 0) {
            printGameField();
            humanTurn();
            printGameField();
            computerTurn();
            printGameField();
        }
        switch (checkStatus) {
            case 1:
                result = "Draw";
                break;
            case 2:
                result = "Winner is Human";
                break;
            case 3:
                result = "Winner is Computer";
                break;
            default:
                break;
        }
        System.out.println(result);
    }

    public static void printGameField() {
        clearConsole(10);
        printHeader();
        printBody();
    }

    public static int isChecksSuccess() {
        if (isFieldFull()) {
            checkStatus = 1;
            return 1;
        } else if (isWin(DOT_X)) {
            checkStatus = 2;
            return 2;
        } else if (isWin(DOT_O)) {
            checkStatus = 3;
            return 3;
        } else {
            checkStatus = 0;
            return 0;
        }
    }

    public static void printHeader() {
        System.out.print("  ");
        for (int y = 0; y < SIZE; y++) {
            System.out.print((y + 1) + " ");
        }
        System.out.print('\n');
    }

    public static void printBody() {
        for (int x = 0; x < SIZE; x++) {
            System.out.print((x + 1) + " ");
            for (int y = 0; y < SIZE; y++) {
                System.out.print(field[x][y] + " ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    public static void humanTurn() {
        if (isFieldFull()) {
            return;
        }

        int x, y;
        Scanner console = new Scanner(System.in);

        do {
            System.out.printf("Insert coordinates X Y\nThey must be between 0 and %d\n", SIZE);
            x = console.nextInt() - 1;  //swapped for convenience
            y = console.nextInt() - 1;  //swapped for convenience

            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
                System.out.println("Try again incorrect");
            } else if (field[x][y] != DOT_EMPTY) {
                System.out.println("already taken");
            } else {
                break;
            }
        } while (true);

        field[x][y] = DOT_X;
    }

    public static void computerTurn() {
        if (isFieldFull()) {
            return;
        }

        isWin(DOT_X);

        int x, y;
        Random rnd = new Random();

        do {
            x = rnd.nextInt(SIZE);
            y = rnd.nextInt(SIZE);
        } while (field[x][y] != DOT_EMPTY);

        for (int x1 = 0; x1 < SIZE; x1++) {
            for (int y1 = 0; y1 < SIZE; y1++) {
                if (fieldProbablyWin[x1][y1] == DOT_O && field[x1][y1] == DOT_EMPTY) {
                    field[x1][y1] = DOT_O;
                    return;
                }
            }
        }

        field[x][y] = DOT_O;
    }

    public static void clearConsole(int lines) {
        for (int i = 0; i < lines; ++i) {
            System.out.println();
        }
    }

    public static boolean isFieldFull() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (field[x][y] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isWin(char symbol) {
        boolean isWin;

        isWin = checkRows(symbol) || checkColumn(symbol) || checkDiagonal(symbol);
        counterDot = 0;

        return isWin;
    }

    private static boolean checkRows(char symbol) {
        counterDot = 0;

        for (int x = 0; x < SIZE; x++) {
            counterDot = 0;
            for (int y = 0; y < SIZE; y++) {
                if (field[x][y] == symbol) {
                    counterDot++;
                    if (counterDot == DOTS_TO_WIN - 1 && y + 1 < SIZE && field[x][y + 1] == DOT_EMPTY) {
                        fieldProbablyWin[x][y + 1] = DOT_O;
                    } else if (counterDot == DOTS_TO_WIN - 1 && y - DOTS_TO_WIN + 1 >= 0 && field[x][y - DOTS_TO_WIN + 1] == DOT_EMPTY) {
                        fieldProbablyWin[x][y - DOTS_TO_WIN + 1] = DOT_O;
                    }
                    if (counterDot == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkColumn(char symbol) {
        counterDot = 0;

        for (int y = 0; y < SIZE; y++) {
            counterDot = 0;
            for (int x = 0; x < SIZE; x++) {
                if (field[x][y] == symbol) {
                    counterDot++;
                    if (counterDot == DOTS_TO_WIN - 1 && x + 1 < SIZE && field[x + 1][y] == DOT_EMPTY) {
                        fieldProbablyWin[x + 1][y] = DOT_O;
                    } else if (counterDot == DOTS_TO_WIN - 1 && x - DOTS_TO_WIN + 1 >= 0 && field[x - DOTS_TO_WIN + 1][y] == DOT_EMPTY) {
                        fieldProbablyWin[x - DOTS_TO_WIN + 1][y] = DOT_O;
                    }
                    if (counterDot == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonal(char symbol) {
        counterDot = 0;

        for (int d1 = 0; d1 < SIZE; d1++) {
            if (field[d1][d1] == symbol) {
                counterDot++;
                if (counterDot == DOTS_TO_WIN - 1 && d1 + 1 < SIZE && field[d1 + 1][d1 + 1] == DOT_EMPTY) {
                    fieldProbablyWin[d1 + 1][d1 + 1] = DOT_O;
                } else if (counterDot == DOTS_TO_WIN - 1 && d1 - DOTS_TO_WIN + 1 >= 0 && field[d1 - DOTS_TO_WIN + 1][d1 - DOTS_TO_WIN + 1] == DOT_EMPTY) {
                    fieldProbablyWin[d1 - DOTS_TO_WIN + 1][d1 - DOTS_TO_WIN + 1] = DOT_O;
                }
                if (counterDot == DOTS_TO_WIN) {
                    return true;
                }
            }
        }
        counterDot = 0;


            for (int x = 0, y = SIZE - 1; y >= 0; x++ ,y--) {
                if (field[x][y] == symbol) {
                    counterDot++;
                    if (counterDot == DOTS_TO_WIN - 1 && x + 1 < SIZE && y - 1 >= 0 && field[x + 1][y - 1] == DOT_EMPTY) {
                        fieldProbablyWin[x + 1][y - 1] = DOT_O;
                    } else if (counterDot == DOTS_TO_WIN - 1 && x - DOTS_TO_WIN + 1 >= 0 && y + DOTS_TO_WIN - 1 < SIZE && field[x - DOTS_TO_WIN + 1][y + DOTS_TO_WIN - 1] == DOT_EMPTY) {
                        fieldProbablyWin[x - DOTS_TO_WIN + 1][y + DOTS_TO_WIN - 1] = DOT_O;
                    }
                    if (counterDot == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        counterDot = 0;

        //secondary diagonals
        for (int y = 1; y < SIZE; y++) {
            counterDot = 0;
            for (int x = 0; x < SIZE - y; x++) {
                if (field[x][x + y] == symbol) {
                    counterDot++;
                    if (counterDot == DOTS_TO_WIN - 1 && x + y + 1 < SIZE && field[x + 1][x + y + 1] == DOT_EMPTY) {
                        fieldProbablyWin[x + 1][x + y + 1] = DOT_O;
                    } else if (counterDot == DOTS_TO_WIN - 1 && x - DOTS_TO_WIN + 1 >= 0 && x + y - DOTS_TO_WIN + 1 >= 0 && field[x - DOTS_TO_WIN + 1][x + y - DOTS_TO_WIN + 1] == DOT_EMPTY) {
                        fieldProbablyWin[x - DOTS_TO_WIN + 1][x + y - DOTS_TO_WIN + 1] = DOT_O;
                    }
                    if (counterDot == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        counterDot = 0;
//
        for (int x = 1; x < SIZE; x++) {
            counterDot = 0;
            for (int y = 0; y < SIZE - x; y++) {
                if (field[x + y][y] == symbol) {
                    counterDot++;
                    if (counterDot == DOTS_TO_WIN - 1 && x + y + 1 < SIZE && field[x + y + 1][y + 1] == DOT_EMPTY) {
                        fieldProbablyWin[x + y + 1][y + 1] = DOT_O;
                    } else if (counterDot == DOTS_TO_WIN - 1 && x + y - DOTS_TO_WIN + 1 >= 0 && y - DOTS_TO_WIN + 1 >= 0 && field[x + y - DOTS_TO_WIN + 1][y - DOTS_TO_WIN + 1] == DOT_EMPTY) {
                        fieldProbablyWin[x + y - DOTS_TO_WIN + 1][y - DOTS_TO_WIN + 1] = DOT_O;
                    }
                    if (counterDot == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        counterDot = 0;

        //other secondary diagonals
        for (int x = 0; x < SIZE; x++) {
            counterDot = 0;
            for (int y = 1; y < SIZE - x; y++) {
                if (field[x + y][SIZE - y] == symbol) {
                    counterDot++;
                    if (counterDot == DOTS_TO_WIN - 1 && x + y + 1 < SIZE && field[x + y + 1][SIZE - y - 1] == DOT_EMPTY) {
                        fieldProbablyWin[x + y + 1][SIZE - y - 1] = DOT_O;
                    } else if (counterDot == DOTS_TO_WIN - 1 && SIZE - y + DOTS_TO_WIN - 1 < SIZE && field[x + y - DOTS_TO_WIN + 1][SIZE - y + DOTS_TO_WIN - 1] == DOT_EMPTY) {
                        fieldProbablyWin[x + y - DOTS_TO_WIN + 1][SIZE - y + DOTS_TO_WIN - 1] = DOT_O;
                    }
                    if (counterDot == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        counterDot = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int x = 0, y = SIZE - 1 - i; y >= 0; x++, y--) {
                if (field[x][y] == symbol) {
                    counterDot++;
                 if (counterDot == DOTS_TO_WIN - 1 && y - 1 >= 0 && field[x + 1][y - 1] == DOT_EMPTY) {
                        fieldProbablyWin[x + 1][y - 1] = DOT_O;
                    } else if (counterDot == DOTS_TO_WIN - 1 && x - DOTS_TO_WIN + 1 >= 0 && field[x - DOTS_TO_WIN + 1][y + DOTS_TO_WIN - 1] == DOT_EMPTY) {
                        fieldProbablyWin[x - DOTS_TO_WIN + 1][y + DOTS_TO_WIN - 1] = DOT_O;
                    }
                    if (counterDot == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
            counterDot = 0;
        }

        return false;
    }

}
