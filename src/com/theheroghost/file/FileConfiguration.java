package com.theheroghost.file;

import com.theheroghost.main.main;

import java.io.*;
import java.util.ArrayList;

public class FileConfiguration {

    private String Path;
    private String Name;

    private File file;
    private File path;


    /**
     * Constructor of FileConfiguration
     *
     * @param Path Path of the Document
     * @param Name Name of the Document
     */
    public FileConfiguration(String Path, String Name) {
        main.main();
        this.Path = Path;
        this.Name = Name;
        file = new File(Path + Name + ".yml");
        path = new File(Path);

        create();
    }

    /**
     * @param count
     * @param with
     * @return returns the String from "param with" multiplied with the "param count".
     */
    private String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

    /**
     * @param Key   The Keyword. New lines possible with "." example: "main.test".
     * @param input The String u want to save.
     */
    public void set(String Key, String input) {
        String[] keys = Key.split("\\.");
        try {
            File tempfile = new File(Path + "temp" + Name);
            tempfile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br2 = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempfile));

            ArrayList<String> lines = new ArrayList<>();
            int i = 0;

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(i, line);
                i++;
            }
            boolean first = false;

            int found = 0;

            String lastfound = null;
            int linelastfound = 0;

            for (int j = 0; j < lines.size(); j++) {
                line = lines.get(j);
                line = line.replace(":", "");
                try {
                    if (first == true) {
                        if (line.startsWith(" ")) {
                            line = line.substring(found * 3);
                            if (!line.startsWith(" ")) {
                                if (found < keys.length) {
                                    if (line.startsWith(keys[found])) {
                                        lastfound = keys[found];
                                        linelastfound = j;
                                        found++;
                                    }
                                }
                                if (found == keys.length) {
                                    if (line.startsWith(keys[keys.length - 1])) {
                                        String space = repeat((keys.length - 1) * 3, " ");
                                        String key = space + keys[keys.length - 1] + ":";
                                        key = key + " " + input;
                                        lines.set(j, key);
                                    }
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    if (line.startsWith(keys[0]) && first == false) {
                        found++;
                        lastfound = keys[0];
                        linelastfound = j;
                        first = true;
                    }
                } catch (Exception e) {
                }
            }

            if (lines.size() == 0) {
                lines.add(0, null);
            }

            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j) != null) {
                    pw.println(lines.get(j));

                }
                if (linelastfound == 0) {
                    linelastfound = lines.size() - 1;
                }
                if (j == linelastfound) {
                    for (int j2 = found; j2 < keys.length; j2++) {
                        String space = repeat(j2 * 3, " ");
                        String key = space + keys[j2] + ":";
                        if (j2 == keys.length - 1) {
                            key = key + " " + input;
                        }
                        pw.println(key);
                    }
                }
            }

            pw.close();
            br.close();
            br2.close();
            file.delete();
            tempfile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File konnte nicht gefunden werden!");
        }
    }

    /**
     * @param Key Its the keypath to find you're saved String.
     * @return returns the String you saved.
     */
    public String getString(String Key) {
        String input = "";
        String[] keys = Key.split("\\.");
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));

            ArrayList<String> lines = new ArrayList<String>();

            int i = 0;

            while ((line = br.readLine()) != null) {
                lines.add(i, line);
                i++;
            }

            boolean first = false;

            int found = 0;

            String lastfound = null;

            for (int j = 0; j < lines.size(); j++) {
                line = lines.get(j);
                line = line.replace(":", "");
                try {
                    if (first == true) {
                        if (line.startsWith(" ")) {
                            line = line.substring(found * 3);
                            if (!line.startsWith(" ")) {
                                if (found < keys.length) {
                                    if (line.startsWith(keys[found])) {
                                        found++;
                                    }
                                }
                                if (found == keys.length) {
                                    if (line.startsWith(keys[keys.length - 1])) {
                                        String key = line.substring(keys[keys.length - 1].length() + 1);
                                        input = key;
                                    }
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    if (line.startsWith(keys[0]) && first == false) {
                        found++;
                        first = true;
                    }
                } catch (Exception e) {
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("File konnte nicht gefunden werden!");
        }
        return input;
    }

    public void create() {
        if (!file.exists()) {
            path.mkdir();
            try {
                file.createNewFile();
                System.out.println("File wurde  erstellt!");
            } catch (IOException e) {
                System.out.println("File konnte nicht erstellt werden!");
            }
        }
    }

    public boolean contains(String Key) {
        String input = "";
        String[] keys = Key.split("\\.");
        boolean contains = false;
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));

            ArrayList<String> lines = new ArrayList<String>();

            int i = 0;

            while ((line = br.readLine()) != null) {
                lines.add(i, line);
                i++;
            }

            boolean first = false;

            int found = 0;

            String lastfound = null;

            for (int j = 0; j < lines.size(); j++) {
                line = lines.get(j);
                line = line.replace(":", "");
                try {
                    if (first == true) {
                        if (line.startsWith(" ")) {
                            line = line.substring(found * 3);
                            if (!line.startsWith(" ")) {
                                if (found < keys.length) {
                                    if (line.startsWith(keys[found])) {
                                        found++;
                                    }
                                }
                                if (found == keys.length) {
                                    if (line.startsWith(keys[keys.length - 1])) {
                                        contains = true;
                                    }
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    if (line.startsWith(keys[0]) && first == false) {
                        found++;
                        first = true;
                    }
                } catch (Exception e) {
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("File konnte nicht gefunden werden!");
        }
        return contains;
    }

    public void remove(String Key) {
        String[] keys = Key.split("\\.");
        try {
            File tempfile = new File(Path + "temp" + Name);
            tempfile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br2 = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempfile));

            ArrayList<String> lines = new ArrayList<>();
            int i = 0;

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(i, line);
                i++;
            }
            boolean first = false;

            int found = 0;

            String lastfound = null;
            int linelastfound = 0;

            for (int j = 0; j < lines.size(); j++) {
                line = lines.get(j);
                line = line.replace(":", "");
                try {
                    if (first == true) {
                        if (line.startsWith(" ")) {
                            line = line.substring(found * 3);
                            if (!line.startsWith(" ")) {
                                if (found < keys.length) {
                                    if (line.startsWith(keys[found])) {
                                        lastfound = keys[found];
                                        linelastfound = j;
                                        found++;
                                    }
                                }
                                if (found == keys.length) {
                                    if (line.startsWith(keys[keys.length - 1])) {
                                        lines.remove(j);
                                    }
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    if (line.startsWith(keys[0]) && first == false) {
                        found++;
                        lastfound = keys[0];
                        linelastfound = j;
                        first = true;
                    }
                } catch (Exception e) {
                }
            }

            if (lines.size() == 0) {
                lines.add(0, null);
            }

            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j) != null) {
                    pw.println(lines.get(j));

                }
            }

            pw.close();
            br.close();
            br2.close();
            file.delete();
            tempfile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File konnte nicht gefunden werden!");
        }
    }

    public void clear() {
        file.delete();
        create();
    }
}
