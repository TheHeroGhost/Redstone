package com.theheroghost.GUI;

import com.theheroghost.main.main;

import javax.swing.*;

public class Window {

    private JFrame jf;
    private int Width;
    private int Height;

    public JFrame getWindow() {
        return jf;
    }

    public Window() {
        main.main();
    }

    public void createWindow(String Name, int width, int height) {
        jf = new JFrame(Name);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(width, height);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);

        jf.setVisible(true);
    }

    public void addDraw(DrawTools d) {
        d.setVisible(true);
        d.setBounds(0, 0, jf.getWidth(), jf.getHeight());
        jf.add(d);
    }

    public void addButton(CustomButton cb) {
        jf.addMouseMotionListener(cb);
        jf.addMouseListener(cb);
    }
}
