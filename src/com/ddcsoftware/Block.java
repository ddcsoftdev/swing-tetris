package com.ddcsoftware;

import java.util.List;
import java.util.ArrayList;

public class Block {
    private final String SHAPE;
    private final int TILE_SIZE;

    private Vector2D center;
    private List<Vector2D> shapeLocation;

    public boolean isMoving;

    public Block(String shape, Vector2D location, int tileSize) {
        this.SHAPE = shape;
        this.TILE_SIZE = tileSize;
        this.center = location;
        this.shapeLocation = buildLocation();
        this.isMoving = true;
    }

    private List<Vector2D> buildLocation() {
        List<Vector2D> list = new ArrayList<>(List.of(center));
        if (SHAPE.equals("square")) {
            list.add(center.add(0, TILE_SIZE));
            list.add(center.add(TILE_SIZE, 0));
            list.add(center.add(TILE_SIZE, TILE_SIZE));
        }
        return list;
    }

    public List<Vector2D> moveBlock() {
        this.isMoving = true;
        for (Vector2D vector2D : shapeLocation) {
            vector2D.y += TILE_SIZE;
            vector2D.x += TILE_SIZE;
        }
        return shapeLocation;
    }

    public List<Vector2D> stopBlock(){
        this.isMoving = false;
        return shapeLocation;
    }
}
