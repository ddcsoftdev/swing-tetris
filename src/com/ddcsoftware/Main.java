package com.ddcsoftware;

public class Main {
    public static void main(String[] args) {
        GameConfig test = new GameConfig();
        test.title = "Windows";
        test.window_size = new Vector2D(800, 800);

        Game game = new Game(test);
    }

}
