package com.theheroghost.main;

import com.theheroghost.GUI.CustomButton;
import com.theheroghost.GUI.DrawTools;
import com.theheroghost.GUI.Window;
import com.theheroghost.utils.Register;
import com.theheroghost.utils.VersionChecker;

import java.awt.*;
import java.util.ArrayList;

public class main {

    private static boolean Enabled;

    private static String Version = "1.0.0";
    private static String Developer = "TheHeroGhost";

    public static ArrayList<CustomButton> Buttons = new ArrayList<CustomButton>();
    public static CustomButton button2 = new CustomButton("test2", 80, 100, 100, 30) {
        @Override
        public void onButtonPressEvent() {

        }
    };

    /**
     * Redstone main class
     *
     * developed by Nico Seifermann
     */
    private static VersionChecker vc = new VersionChecker();
    public static void main() {

        if(!Enabled) {
            System.out.println("Der Redstone hat gestartet!");
            Enabled = true;
        }

        if(vc.versionUptodate()) {
            System.out.println("Du hast die neuste Version des Redstone!");
        } else {
            System.out.println("Dein Redstone ist veraltet. Update es!");
        }
    }

    private static Window w = new Window();
    private static DrawTools dt = new DrawTools();
    private static ArrayList<String> test = new ArrayList<String>();

    public static void main(String[] args) {
        main();
        w.createWindow("test", 500, 500);
        w.addDraw(dt);
        w.addButton(button2);
    }

    public static String getVersion() {
        return Version;
    }

    public static String getDeveloper() {
        return Developer;
    }
}
