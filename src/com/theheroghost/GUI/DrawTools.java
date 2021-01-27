package com.theheroghost.GUI;


import com.theheroghost.main.main;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class DrawTools extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        for(int i = 0; i < main.Buttons.size(); i++) {
            CustomButton cb = main.Buttons.get(i);
            cb.Draw(g);
        }

        Draw(g);

        setDoubleBuffered(true);

        repaint();
    }

    public void Draw(Graphics g) {

    }
}
