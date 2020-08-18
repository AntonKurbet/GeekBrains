package ru.geekbrains.java1.lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Map extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;

    private JButton[][] field;

    Map() {
        setBackground(Color.DARK_GRAY);
    }

    private void initField(int fieldSizeX, int fieldSizeY) {
        field = new JButton[fieldSizeY][fieldSizeX];

        setLayout(new GridLayout(fieldSizeX, fieldSizeY));
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = new JButton();
                //field[y][x].setText("X");
                field[y][x].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //System.out.println("123");
                    }
                });
                field[y][x].setBackground(Color.LIGHT_GRAY);
                this.add(field[y][x]);
            }
        }

        this.revalidate();
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        initField(fieldSizeX,fieldSizeY);
    }
}
