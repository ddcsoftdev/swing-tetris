package com.ddcsoftware;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileManager {

    private final static String CUBE_NAME = "cube.png";

    public final static char CUBE = 'c';

    public static String [] getDirectories(String dir) {
        File directoryPath = new File(dir);
        return directoryPath.list();
    }

    public static Image getImageEntry(String path){
        Toolkit t = Toolkit.getDefaultToolkit();
        return t.getImage(path);
    }

    public static char getCharacterFromName(String path){
        Path p = Paths.get(path);
        return getCharacterFromPath(p);
    }

    private static char getCharacterFromPath(Path path){
        String p = path.getFileName().toString();
        char c = '0';
        if (p.equals(CUBE_NAME)){
            c = CUBE;
        }
        return c;
    }
}
