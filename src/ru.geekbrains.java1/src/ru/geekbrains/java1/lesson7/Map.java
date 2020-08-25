package ru.geekbrains.java1.lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ru.geekbrains.java1.lesson7.TicTacToe.DOT_AI;
import static ru.geekbrains.java1.lesson7.TicTacToe.DOT_HUMAN;

public class Map extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static boolean initialized;
    private static boolean isGameOver;

    private static int mapWidth;
    private static int mapHeight;
    private static int cellSizeX;
    private static int cellSizeY;

    private JButton[][] field;

    Map() {
        mapWidth = this.getWidth();
        mapHeight = this.getHeight();
        cellSizeX = mapWidth / fieldSizeX;
        cellSizeY = mapHeight / fieldSizeY;

        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                onClick(e);
            }
        });
    }

    private void onClick(MouseEvent e) {
        if (isGameOver || !initialized) return;
        int cellX = e.getX() / cellSizeX;
        int cellY = e.getY() / cellSizeY;
        if (!TicTacToe.isValidCell(cellX, cellY) || !TicTacToe.isEmptyCell(cellX, cellY))
            return;
        TicTacToe.setFieldCell(cellX,cellY, DOT_HUMAN);
        if (TicTacToe.checkEndGame(DOT_HUMAN) == TicTacToe.STATE_WIN_HUMAN)
            return;
        TicTacToe.aiTurn();
        repaint();
        if (TicTacToe.checkEndGame(TicTacToe.DOT_AI) == TicTacToe.STATE_WIN_AI)
            return;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!initialized) return;

        paintField(g);

        paintMoves(g);

    }

    private void paintMoves(Graphics g) {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                switch (TicTacToe.getFieldCell(x,y)) {
                    case DOT_HUMAN:

                        break;
                    case DOT_AI:

                        break;
                }
            }
        }
    }

    private void paintField(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        for (int x = 1; x < fieldSizeX; x++) {
            int x1 = x * cellSizeX;
            g.drawLine(x1, 0, x1, mapHeight);
        }

        for (int y = 1; y < fieldSizeY; y++) {
            int y1 = y * cellSizeY;
            g.drawLine(0, y1, mapWidth,y1);
        }
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;

        initialized = true;
        repaint();

    }
}
