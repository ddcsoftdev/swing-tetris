package com.ddcsoftware;

public class Vector2D {
    public int y;
    public int x;

    public Vector2D(int y, int x){
        this.y = y;
        this.x = x;
    }

    public Vector2D substract(int y, int x){
        this.y -= y;
        this.x -= x;
        return this;
    }
}
