package ru.geekbrains.java1.lesson7;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static int fieldSizeY;
    private static int fieldSizeX;
    private static int dotNumber;
    private static int lastX;
    private static int lastY;
    private static char[][] field;

    private static void initField(/*int fSX, int fSY, int dN*/) {
//        fieldSizeX = fSX;
//        fieldSizeY = fSY;
//        dotNumber = dN;
        fieldSizeX = 5;
        fieldSizeY = 7;
        dotNumber = 4;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("+");
        for (int x = 0; x < fieldSizeX * 2 + 1; x++)
            System.out.print((x % 2 == 0) ? "-" : x / 2 + 1);
        System.out.println();

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++)
                System.out.print(field[y][x] + "|");
            System.out.println();
        }

        for (int x = 0; x <= fieldSizeX * 2 + 1; x++)
            System.out.print("-");
        System.out.println();
    }

    private static void humanTurn() {
        do {
            System.out.printf("Введите координаты хода X (от 1 до %d) и Y (от 1 до %d) через пробел >>> \n",
                              fieldSizeX,fieldSizeY);
            lastX = SCANNER.nextInt() - 1;
            lastY = SCANNER.nextInt() - 1;
        } while (!isValidCell(lastX, lastY) || !isEmptyCell(lastX, lastY));
        field[lastY][lastX] = DOT_HUMAN;
    }

    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y)) return false;
            }
        }
        return true;
    }

    private static boolean checkWinCell(char c, int moveX, int moveY, int n) {
        int i;
        int x = moveX;
        int y = moveY;
        for (i = 1; i < n; i++) { //UR
            x+=i; y-=i;
            if (!isValidCell(x, y) || field[y][x] != c) break;
        }
        if (i < dotNumber) {
            x = moveX;
            y = moveY;
            for (i = 1; i < n; i++) { //R
                x+=i;
                if (!isValidCell(x,y) || field[y][x] != c) break;
            }
        } else return true;
        if (i < n) {
            x = moveX;
            y = moveY;
            for (i = 1; i < n; i++) { //DR
                x+=i; y+=i;
                if (!isValidCell(x,y) || field[y][x] != c) break;
            }
        } else return true;
        if (i < n) {
            x = moveX;
            y = moveY;
            for (i = 1; i < n; i++) { //D
                y+=i;
                if (!isValidCell(x,y) || field[y][x] != c) break;
            }
        } else return true;
        return i == n;
    }

    private static boolean checkWin(char c) {
        return checkWinCell(c, lastX, lastY, dotNumber);
    }

    private static void aiTurn() {
        for (int i = dotNumber; i > 1; i--) {
            for (int x = 0; x < fieldSizeX; x++) {
                for (int y = 0; y < fieldSizeY; y++) {
                    if (isEmptyCell(x, y) && checkWinCell(DOT_HUMAN, x, y, i)) {
                        field[y][x] = DOT_AI;
                        return;
                    }
                }
            }
        }
    }

    private static boolean isAICell(int x, int y) {
        return field[y][x] == DOT_AI;
    }

    public static void main(String[] args) {
        String answer;
        do {
            initField();
            printField();
            while (true) {
                humanTurn();
                if (checkEndGame(DOT_HUMAN, "Human win!")) break;
                aiTurn();
                if (checkEndGame(DOT_AI, "Computer win!")) break;
            }
            System.out.println("Wanna play again? (y/n) >>> ");
            answer = SCANNER.next();
        } while (answer.equals("y"));
        SCANNER.close();
    }

    private static boolean checkEndGame(char dot, String winMessage) {
        printField();

        if (checkWin(dot)) {
            System.out.println(winMessage);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Draw!");
            return true;
        }
        return false;
    }
}
