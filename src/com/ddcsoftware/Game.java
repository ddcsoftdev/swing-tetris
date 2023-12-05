package com.ddcsoftware;

import javax.swing.*;

import java.util.List;
import java.util.Random;

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
        this.map = new Map(config.window_size.substract(0, 150));
        this.renderer = new Renderer(this.map, this.map.getDimension());
        this.instance = new GameInstance();
        int temp = 0;
        //start game loop
        while (true) {

            try {
                temp = updateFrame(temp);
            } catch (Exception er) {

            }

            renderer.updateMap(map);
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
        if (temp == 0){
            instance.activeBlock = spawnBlock();
        }
        instance.activeBlock.moveBlock();
        for (Vector2D vector : instance.activeBlock.moveBlock()){
            try{
                instance.activeBlock.isMoving = map.updateMap(vector, tileSize);
               if (!instance.activeBlock.isMoving)
                   return 0;
            } catch (Exception e){

            }
        }
        return temp + 50;
    }

    public Block spawnBlock(){
        String shape = "square";
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
