package com.ddcsoftware;

import javax.swing.*;

import static java.lang.Thread.sleep;

public class Game {
    private JFrame window;
    private Map map;
    private Renderer renderer;

    private final int FRAME_SPEED = 250;

    //Takes file with the game configuration
    public Game(GameConfig config){
        this.window = new JFrame(config.title);
        window.setSize(config.window_size.x, config.window_size.y);
        window.setVisible(true);

        //offset map so UI fits in map
        this.map = new Map(config.window_size.substract(0, 150));
        this.renderer = new Renderer(this.map, this.map.getDimension());

        int temp = 50;
        //start game loop
        while (true){

            temp = updateFrame(temp);

            renderer.updateMap(map);
            window.add(renderer);
            window.setVisible(true);
            //window.repaint();
            try{
                sleep(FRAME_SPEED);
            } catch (Exception e)
            {
                System.out.printf("Error: %s\n", e.getMessage());
                break;
            }
        }
    }
//TODO: NEEDS REDOING
    private int updateFrame(int temp){

        return temp;
    }
}
