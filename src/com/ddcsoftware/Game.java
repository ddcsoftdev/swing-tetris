package com.ddcsoftware;

import javax.swing.*;

import java.util.*;
import java.util.Map.Entry;

import static java.lang.Thread.sleep;

public class Game {
    private JFrame window;
    private Map map;
    private Renderer renderer;
    private GameInstance instance;
    private final int FRAME_SPEED = 250;

    //Takes file with the game configuration
    public Game(GameConfig config) {
        this.window = new JFrame(config.title);
        window.setSize(config.window_size.x, config.window_size.y);
        window.setVisible(true);

        //offset map so UI fits in map
        this.map = new Map(config.window_size.subtract(0, 150));
        this.renderer = new Renderer(this.map, this.map.getDimension());
        this.instance = new GameInstance();
        int temp = 0;
        //start game loop
        while (true) {

            try {
                temp = updateFrame(temp);
            } catch (Exception er) {
                temp = 0;
            }

            // renderer.updateMap(map);
            window.add(renderer);
            window.setVisible(true);
            //window.repaint();
            try {
                sleep(FRAME_SPEED);
            } catch (Exception e) {
                System.out.printf("Error: %s\n", e.getMessage());
                break;
            }
        }
    }

    //TODO: NEEDS REDOING
    private int updateFrame(int temp) throws Exception {
        int temp2 = 300;
        int tileSize = 50;

        //if y = 0 spawn new block
        if (temp == 0 || !instance.activeBlock.isMoving) {
            instance.activeBlock = spawnBlock();
        }

        HashMap<Integer, Integer> lowestBlocks = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> highestBlocks = new HashMap<Integer, Integer>();
        //move block
        for (Vector2D vector : instance.activeBlock.moveBlock()) {

            if (!lowestBlocks.containsKey(vector.x)) {
                lowestBlocks.put(vector.x, vector.y);
            } else {
                if (lowestBlocks.get(vector.x) < vector.y) {
                    lowestBlocks.replace(vector.x, vector.y);
                }
            }

            if (!highestBlocks.containsKey(vector.x)) {
                highestBlocks.put(vector.x, vector.y);
            } else {
                if (highestBlocks.get(vector.x) > vector.y) {
                    highestBlocks.replace(vector.x, vector.y);
                }
            }

            try {
                map.updateMap(vector, tileSize);
            } catch (Exception e) {
                System.out.println("Error");
            }
        }

        for (Entry<Integer, Integer> entry : lowestBlocks.entrySet()) {
            if (!map.isInRange(new Vector2D(entry.getValue() + tileSize, entry.getKey()))) {
                instance.activeBlock.isMoving = false;
                return 0;
            }
            if (map.getMap()[entry.getValue() + tileSize][entry.getKey()] == FileManager.BLOCK) {
                instance.activeBlock.isMoving = false;
                cleanUp(highestBlocks, tileSize);
                return 0;
            }
        }

        if (instance.activeBlock.isMoving) {
            cleanUp(highestBlocks, tileSize);
        }

        return temp + 50;
    }

    public void cleanUp(HashMap<Integer, Integer> highestBlocks, int tileSize) {
        for (Entry<Integer, Integer> entry : highestBlocks.entrySet()) {
            try {
                map.setTile(new Vector2D(entry.getValue() - tileSize, entry.getKey()), FileManager.EMPTY);
            } catch (Exception e) {
                System.out.println("ERROR" + e.toString());
            }
        }
    }

    public Block spawnBlock() {
        //TODO: implement real random
        String shape = "O";
        int max = 6;
        int min = 1;
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        switch (random_int){
            case 1:
                shape = "I";
                break;
            case 2:
                shape = "T";
                break;
            case 3:
                shape = "S";
                break;
            case 4:
                shape = "Z";
                break;
            case 5:
                shape = "L";
                break;
            case 6:
                shape = "J";
                break;
        }

        //TODO: x has to be random within range
        Vector2D location = new Vector2D(0, 300);
        int tileSize = 50;
        return new Block(shape, location, tileSize);
    }

    public static class GameConfig {
        //USE JACKSON INSTEAD OF THIS FOR JSON
        public String title;
        public Vector2D window_size;
    }

    public class GameInstance {
        public Block activeBlock;
    }
}
