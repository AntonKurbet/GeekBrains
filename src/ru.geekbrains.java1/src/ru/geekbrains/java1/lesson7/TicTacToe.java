package ru.geekbrains.java1.lesson7;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static final char DOT_HUMAN = 'X';
    static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    static final int STATE_NONE = 0;
    static final int STATE_WIN_HUMAN = 1;
    static final int STATE_WIN_AI = 2;
    static final int STATE_DRAW = 3;
    static final int STATE_CONTINUE = 4;
    private static int fieldSizeY;
    private static int fieldSizeX;
    private static int dotNumber;
    private static int lastX;
    private static int lastY;
    private static char[][] field;

    public static void initField(int fSX, int fSY, int dN) {
        fieldSizeX = fSX;
        fieldSizeY = fSY;
        dotNumber = dN;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
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

    public static boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    public static boolean isValidCell(int x, int y) {
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

    private static boolean checkWinCell(char c, int x, int y, int n) {
        int ur = 0, r = 0, dr = 0, d = 0;
        for (int i = 0; i < n; i++) { //
            if (isValidCell(x + i, y - i) && (field[y - i][x + i] == c)) ur++;
            if (isValidCell(x + i, y)        && (field[y][x + i] == c))     r++;
            if (isValidCell(x + i, y + i) && (field[y + i][x + i] == c)) dr++;
            if (isValidCell(x, y + i)        && (field[y + i][x] == c))     d++;
        }
        return ur == n || r == n || dr == n || d ==n;
    }

    private static boolean checkWin(char c, int moveX, int moveY, int dots) {
        for (int y = 0; y <= fieldSizeY; y++)
            for (int x = 0; x <= fieldSizeX; x++)
                if (isValidCell(x,y)  && checkWinCell(c, x, y, dots))
                        return true;
        return false;
    }

    static void aiTurn() {

        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isEmptyCell(x, y)) {
                    field[y][x] = DOT_AI;
                    if (checkWin(DOT_AI, x, y, dotNumber))  return;
                    else field[y][x] = DOT_EMPTY;
                }
            }
        }

        for (int i = dotNumber; i > 1; i--) {
            for (int x = 0; x < fieldSizeX; x++) {
                for (int y = 0; y < fieldSizeY; y++) {
                    if (isEmptyCell(x, y)) {
                        field[y][x] = DOT_HUMAN;
                        if (checkWin(DOT_HUMAN, x, y, i)) {
                            field[y][x] = DOT_AI;
                            return;
                        } else field[y][x] =DOT_EMPTY;
                    }
                }
            }
        }
    }

    private static boolean isAICell(int x, int y) {
        return field[y][x] == DOT_AI;
    }

    public static int checkEndGame(char dot) {
        if (checkWin(dot,lastX,lastY,dotNumber)) {
            return dot == DOT_HUMAN ? STATE_WIN_HUMAN : STATE_WIN_AI ;
        }
        if (checkDraw()) {
            return STATE_DRAW;
        }
        return STATE_CONTINUE;
    }

    public static void setFieldCell(int cellX, int cellY, char c) {
        field[cellY][cellX] = c;
    }

    public static char getFieldCell(int cellX, int cellY) {
        return field[cellY][cellX];
    }
}
