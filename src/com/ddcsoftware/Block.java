package com.ddcsoftware;

import java.util.List;
import java.util.ArrayList;

public class Block {
    private final String SHAPE;
    private final int TILE_SIZE;

    private Vector2D firstBlock;
    private List<Vector2D> shapeLocation;

    public boolean isMoving;

    public Block(String shape, Vector2D location, int tileSize) {
        this.SHAPE = shape;
        this.TILE_SIZE = tileSize;
        this.firstBlock = location;
        this.shapeLocation = buildLocation();
        this.isMoving = true;
    }

    private List<Vector2D> buildLocation() {
        List<Vector2D> list = new ArrayList<>(List.of(firstBlock));
        if (SHAPE.equals("O")) {
            list.add(firstBlock.add(0, TILE_SIZE));
            list.add(firstBlock.add(TILE_SIZE, 0));
            list.add(firstBlock.add(TILE_SIZE, TILE_SIZE));
        }
        else if (SHAPE.equals("I")){
            list.add(firstBlock.add(0, TILE_SIZE));
            list.add(firstBlock.add(0, TILE_SIZE * 2));
            list.add(firstBlock.add(0, TILE_SIZE * 3));
        }
        else if (SHAPE.equals("T")){
            list.add(firstBlock.add(TILE_SIZE, TILE_SIZE));
            list.add(firstBlock.add(TILE_SIZE, 0));
            list.add(firstBlock.add(TILE_SIZE, -TILE_SIZE));
        }
        else if (SHAPE.equals("S")){
            list.add(firstBlock.add(0, TILE_SIZE));
            list.add(firstBlock.add(TILE_SIZE, 0));
            list.add(firstBlock.add(TILE_SIZE, -TILE_SIZE));
        }
        else if (SHAPE.equals("Z")){
            list.add(firstBlock.add(0, -TILE_SIZE));
            list.add(firstBlock.add(TILE_SIZE, 0));
            list.add(firstBlock.add(TILE_SIZE, TILE_SIZE));
        }
        else if (SHAPE.equals("L")){
            list.add(firstBlock.add(TILE_SIZE, 0));
            list.add(firstBlock.add(TILE_SIZE, -TILE_SIZE));
            list.add(firstBlock.add(TILE_SIZE, TILE_SIZE * (-2)));
        }
        else if (SHAPE.equals("J")){
            list.add(firstBlock.add(TILE_SIZE, 0));
            list.add(firstBlock.add(TILE_SIZE, TILE_SIZE));
            list.add(firstBlock.add(TILE_SIZE, TILE_SIZE * 2));
        }
        return list;
    }

    public List<Vector2D> moveBlock() {
        this.isMoving = true;
        for (Vector2D vector2D : shapeLocation) {
            vector2D.y += TILE_SIZE;
            //vector2D.x += TILE_SIZE;
        }
        return shapeLocation;
    }

    public List<Vector2D> stopBlock(){
        this.isMoving = false;
        return shapeLocation;
    }
}
