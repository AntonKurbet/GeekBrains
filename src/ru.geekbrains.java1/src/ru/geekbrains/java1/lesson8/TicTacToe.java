package ru.geekbrains.java1.lesson8;

public class TicTacToe {

    private static int fieldSizeY;
    private static int fieldSizeX;
    private static int dotNumber;
    private static TicTacDots[][] field;

    public static void initField(int fSX, int fSY, int dN) {
        fieldSizeX = fSX;
        fieldSizeY = fSY;
        dotNumber = dN;

        field = new TicTacDots[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = TicTacDots.DOT_EMPTY;
            }
        }
    }

    public static boolean isEmptyCell(int x, int y) {
        return field[y][x] == TicTacDots.DOT_EMPTY;
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

    private static boolean checkWinCell(TicTacDots c, int x, int y, int n) {
        int ur = 0, r = 0, dr = 0, d = 0;
        for (int i = 0; i < n; i++) { //
            if (isValidCell(x + i, y - i) && (field[y - i][x + i] == c)) ur++;
            if (isValidCell(x + i, y)        && (field[y][x + i] == c))     r++;
            if (isValidCell(x + i, y + i) && (field[y + i][x + i] == c)) dr++;
            if (isValidCell(x, y + i)        && (field[y + i][x] == c))     d++;
        }
        return ur == n || r == n || dr == n || d ==n;
    }

    private static boolean checkWin(TicTacDots c, int dots) {
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
                    field[y][x] = TicTacDots.DOT_AI;
                    if (checkWin(TicTacDots.DOT_AI, dotNumber))  return;
                    else field[y][x] = TicTacDots.DOT_EMPTY;
                }
            }
        }

        for (int i = dotNumber; i > 1; i--) {
            for (int x = 0; x < fieldSizeX; x++) {
                for (int y = 0; y < fieldSizeY; y++) {
                    if (isEmptyCell(x, y)) {
                        field[y][x] = TicTacDots.DOT_HUMAN;
                        if (checkWin(TicTacDots.DOT_HUMAN, i)) {
                            field[y][x] = TicTacDots.DOT_AI;
                            return;
                        } else field[y][x] = TicTacDots.DOT_EMPTY;
                    }
                }
            }
        }
    }

    public static GameStates checkEndGame(TicTacDots dot) {
        if (checkWin(dot,dotNumber)) {
            return dot == TicTacDots.DOT_HUMAN ? GameStates.STATE_WIN_HUMAN : GameStates.STATE_WIN_AI ;
        }
        if (checkDraw()) {
            return GameStates.STATE_DRAW;
        }
        return GameStates.STATE_CONTINUE;
    }

    public static void setFieldCell(int cellX, int cellY, TicTacDots c) {
        field[cellY][cellX] = c;
    }

    public static TicTacDots getFieldCell(int cellX, int cellY) {
        return field[cellY][cellX];
    }
}
