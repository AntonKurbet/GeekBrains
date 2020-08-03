import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    // 1. Полностью разобраться с кодом;
    // 2. Переделать проверку победы, чтобы она не была реализована просто набором условий.
    // 3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х7 и количества фишек 4.
    // 4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока, и пытаться выиграть сам.

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

    private static void initField() {
        //fieldSizeX = 3;
        //fieldSizeY = 3;
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

    private static void aiTurn() {
        do {
            lastX = RANDOM.nextInt(fieldSizeX);
            lastY = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(lastX, lastY));
        field[lastY][lastX] = DOT_AI;
    }

    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y)) return false;
            }
        }
        return true;
    }

    // задача 2
    private static boolean checkWin2(char c) {
        return checkHor2(c) | checkVer2(c) | checkDia2(c);
    }
    // для квадратного поля с количеством фишек равным его размеру
    private static boolean checkDia2(char c) {
        boolean result1 = true;
        boolean result2 = true;
        for (int x = 0; x < fieldSizeX; x++) {
            result1 = result1 && field[x][x] == c;
            result2 = result2 && field[x][fieldSizeX - x - 1] == c;
        }
        return result1 | result2;
    }
    private static boolean checkVer2(char c) {
        boolean result = false;
        for (int x = 0; x < fieldSizeX; x++) {
            result = true;
            for (int y = 0; y < fieldSizeY; y++) {
                result = result && field[y][x] == c;
            }
            if (result) break;
        }
        return result;
    }
    private static boolean checkHor2(char c) {
        boolean result = false;
        for (int y = 0; y < fieldSizeY; y++) {
            result = true;
            for (int x = 0; x < fieldSizeX; x++) {
                result = result && field[y][x] == c;
            }
            if (result) break;
        }
        return result;
    }

    // задача 3
    private static boolean checkWin3(char c) {
        return checkHorline3(c) | checkVerLine3(c) | checkDiaLine3(c);
    }
    private static boolean checkHorline3(char c) {
        int sum = 0;
        for (int i = 0; i < fieldSizeX - dotNumber; i ++) {
            sum = 0;
            for (int x = 0; x < dotNumber; x++) {
                if (field[lastY][x + i] == c) sum = sum +  1;
                else sum = 0;
                if (sum == dotNumber) break;
            }
            if (sum == dotNumber) break;
        }
        return sum == dotNumber;
    }
    private static boolean checkVerLine3(char c) {
        int sum = 0;
        for (int i = 0; i < fieldSizeY - dotNumber; i ++) {
            sum = 0;
            for (int y = 0; y < dotNumber; y++) {
                if (field[y + i][lastX] == c) sum = sum +  1;
                else sum = 0;
                if (sum == dotNumber) break;
            }
            if (sum == dotNumber) break;
        }
        return sum == dotNumber;
    }
    private static int diaFunc1 (int x) {
        // equation of diagonal left to right
        return x - lastX + lastY;
    }
    private static int diaFunc2 (int x) {
        // equation of diagonal right to left
        return -x + lastX + lastY;
    }
    private static boolean checkDiaLine3(char c) {
        int sum = 0;
        for (int x = 0; x<fieldSizeX; x++) {
            int y = diaFunc1(x);
            if (y >= 0 && y < fieldSizeY) {
                if (field[y][x] == c) sum = sum + 1;
                else sum = 0;
                if (sum == dotNumber) break;
            }
        }

        if (sum != dotNumber) {
            sum = 0;
            for (int x = fieldSizeX - 1; x >= 0; x--) {
                int y = diaFunc2(x);
                if (y >= 0 && y < fieldSizeY) {
                    if (field[y][x] == c) sum = sum + 1;
                    else sum = 0;
                    if (sum == dotNumber) break;
                }
            }
        }
        return sum == dotNumber;
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
        //if (checkWin2(dot)) {
        if (checkWin3(dot)) {
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
