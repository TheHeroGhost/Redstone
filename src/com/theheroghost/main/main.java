package com.theheroghost.main;

import com.theheroghost.GUI.CustomButton;
import com.theheroghost.GUI.DrawTools;
import com.theheroghost.GUI.Window;
import com.theheroghost.utils.Register;

import java.util.ArrayList;

public class main {

    private static boolean Enabled;

    private static String Version = "1.0.0";
    private static String Developer = "TheHeroGhost";

    public static ArrayList<CustomButton> Buttons = new ArrayList<CustomButton>();

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

    public static void main(String[] args) {
    }

    public static String getVersion() {
        return Version;
    }

    public static String getDeveloper() {
        return Developer;
    }
}
