package com.ddcsoftware;

public class Main {
    public static void main(String[] args) {
        Game.GameConfig test = new Game.GameConfig();
        test.title = "Windows";
        test.window_size = new Vector2D(800, 800);

        Game game = new Game(test);
    }

}
