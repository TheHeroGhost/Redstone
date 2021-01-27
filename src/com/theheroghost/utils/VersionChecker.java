package com.theheroghost.utils;

import com.theheroghost.main.main;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Optional;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

public class VersionChecker {

    URL url;

    public boolean versionUptodate() {
        boolean uptoDate = false;
        try {
            url = new URL("https://github.com/TheHeroGhost/Redstone/blob/Redstone/README.md");
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            String line;
            while(scanner.hasNext()) {
                line = scanner.nextLine();
                if(line.startsWith("<p>Version")) {
                    line = line.substring(12, 17);
                    if(line.equalsIgnoreCase(main.getVersion())) {
                        uptoDate = true;
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uptoDate;
    }
}
