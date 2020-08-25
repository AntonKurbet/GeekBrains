package ru.geekbrains.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;
    private static final int DOT_PADDING = 10;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static GameStates gameState = GameStates.STATE_NONE;

    private static int mapWidth;
    private static int mapHeight;
    private static int cellSizeX;
    private static int cellSizeY;

    Map() {
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
        if (gameState != GameStates.STATE_CONTINUE) return;
        int cellX = e.getX() / cellSizeX;
        int cellY = e.getY() / cellSizeY;

        if (!TicTacToe.isValidCell(cellX, cellY) || !TicTacToe.isEmptyCell(cellX, cellY))
            return;

        TicTacToe.setFieldCell(cellX,cellY, TicTacDots.DOT_HUMAN);

        gameState = TicTacToe.checkEndGame(TicTacDots.DOT_HUMAN);
        if ( gameState == GameStates.STATE_WIN_HUMAN || gameState == GameStates.STATE_DRAW) {
            repaint();
            return;
        }

        TicTacToe.aiTurn();
        repaint();

        gameState = TicTacToe.checkEndGame(TicTacDots.DOT_AI);
        if (gameState == GameStates.STATE_WIN_AI || gameState == GameStates.STATE_DRAW) {
            repaint();
            return;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (gameState == GameStates.STATE_NONE) return;

        paintField(g);
        paintMoves(g);

        if (gameState != GameStates.STATE_CONTINUE) paintBanner(g);

    }

    private static void paintBanner(Graphics g) {
        String message;
        switch (gameState) {
            case STATE_DRAW:
                message = "Draw!";
                break;
            case STATE_WIN_HUMAN:
                message = "Human wins!";
                break;
            case STATE_WIN_AI:
                message = "Computer wins!";
                break;
            case STATE_NONE:
            case STATE_CONTINUE:
            default:
                message = "Shit Happens!";
        }

        g.drawString(message, DOT_PADDING, mapHeight / 2);
    }

    private void paintMoves(Graphics g) {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                switch (TicTacToe.getFieldCell(x,y)) {
                    case DOT_HUMAN:
                        g.drawLine(x * cellSizeX + DOT_PADDING, y * cellSizeY + DOT_PADDING,
                                (x + 1) * cellSizeX - DOT_PADDING, (y + 1) * cellSizeY - DOT_PADDING);
                        g.drawLine(x * cellSizeX + DOT_PADDING, (y + 1) * cellSizeY - DOT_PADDING,
                                (x + 1) * cellSizeX - DOT_PADDING, y * cellSizeY + DOT_PADDING);

                        break;
                    case DOT_AI:
                        g.drawOval(x * cellSizeX + DOT_PADDING, y * cellSizeY + DOT_PADDING,
                                cellSizeX - 2 * DOT_PADDING, cellSizeY - 2 * DOT_PADDING);
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

    void startNewGame(int mode, int fSizeX, int fSizeY, int wLength) {
        fieldSizeX = fSizeX;
        fieldSizeY = fSizeY;

        gameState = GameStates.STATE_CONTINUE;

        TicTacToe.initField(fieldSizeX,fieldSizeY,wLength);
        mapWidth = this.getWidth();
        mapHeight = this.getHeight();
        cellSizeX = mapWidth / fieldSizeX;
        cellSizeY = mapHeight / fieldSizeY;

        repaint();
    }
}
