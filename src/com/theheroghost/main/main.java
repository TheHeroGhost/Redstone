package com.theheroghost.main;

import com.theheroghost.GUI.CustomButton;
import com.theheroghost.GUI.DrawTools;
import com.theheroghost.GUI.Window;

import java.util.ArrayList;

public class main {

    private static boolean Enabled;

    private static String Version = "1.0.0";
    private static String Developer = "TheHeroGhost";

    public static ArrayList<CustomButton> Buttons = new ArrayList<CustomButton>();
    public static CustomButton button = new CustomButton("test", 30,50, 100, 30);

    /**
     * Redstone main class
     *
     * developed by Nico Seifermann
     */

    public static void main() {
        if(!Enabled) {
            System.out.println("Der Redstone hat gestartet!");
            Enabled = true;

        }
    }

    private static Window w = new Window();
    private static DrawTools dt = new DrawTools();

    public static void main(String[] args) {
        w.createWindow("test", 500, 500);
        w.addDraw(dt);
        w.addButton(button);
    }

    public static String getVersion() {
        return Version;
    }

    public static String getDeveloper() {
        return Developer;
    }
}
