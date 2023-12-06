package com.ddcsoftware;


import cn.hutool.core.io.file.FileNameUtil;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Renderer extends Canvas {
    private Map map;
    private Vector2D dimension;
    private Dictionary<Character, Image> images;
    private final int TILE_OFFSET = 50;

    public Renderer(Map map, Vector2D dimension) {
        this.map = map;
        this.dimension = dimension;
        this.images = initDictionary();

    }

    //Inits dictionary with images from local image directory
    private static Dictionary<Character, Image> initDictionary() {
        Dictionary<Character, Image> dict = new Hashtable<>();
        String path = System.getProperty("user.dir") + "/images";
        String[] img_files = FileManager.getDirectories(path);
        for (String s : img_files) {
            char c = FileManager.getCharacterFromName(s);
            Image i = FileManager.getImageEntry(path + "/" + s);
            dict.put(c, i);
        }
        return dict;
    }

    public void updateMap(Map map) {
        this.map = map;
    }

    @Override
    public void paint(Graphics g) {
        for (int y = 0; y < dimension.y; y += TILE_OFFSET) {

            for (int x = 0; x < dimension.x; x += TILE_OFFSET) {
                if (x == 0 || x == dimension.x - TILE_OFFSET) {
                    Image i = images.get(FileManager.BLOCK);
                    g.drawImage(i, x, y, TILE_OFFSET, TILE_OFFSET, this);
                } else if (y == dimension.y - TILE_OFFSET) {
                    Image i = images.get(FileManager.BLOCK);
                    g.drawImage(i, x, y, TILE_OFFSET, TILE_OFFSET, this);
                } else if (map.getMap()[y][x] == FileManager.BLOCK) {
                    Image i = images.get(FileManager.BLOCK);
                    g.drawImage(i, x, y, TILE_OFFSET, TILE_OFFSET, this);
                }
            }
        }
    }
}
