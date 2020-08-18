package ru.geekbrains.java1.lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

public class Map extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;
    public static int fieldSizeX;
    public static int fieldSizeY;

    private JButton[][] field;

    Map() {
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paint(g);

        render((Graphics2D) g);
    }

    private void render(Graphics2D g) {
        if (fieldSizeX <= 0 && fieldSizeY <= 0) return;

        int cellSizeX = this.getWidth() / fieldSizeX;
        int cellSizeY = this.getHeight() / fieldSizeY;

        Graphics2D g2d = g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setPaint(Color.DARK_GRAY);

        for (int x = 1; x < fieldSizeX; x++) {
            Line2D lin = new Line2D.Float(x * cellSizeX, 0, x * cellSizeX, this.getHeight());
            g2d.draw(lin);
        }

        for (int y = 1; y < fieldSizeY; y++) {
            Line2D lin = new Line2D.Float(0, y * cellSizeY, this.getWidth(),y * cellSizeY);
            g2d.draw(lin);
        }
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;

        repaint();
        //revalidate();
    }
}
