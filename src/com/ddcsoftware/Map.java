package com.ddcsoftware;

public class Map {
    private char[][] map;
    private final Vector2D dimension;

    public Map(Vector2D dimension) {
        this.dimension = dimension;
        this.map = new char[dimension.y][dimension.x];
    }

    //Checks if coordinates are in range of map
    public boolean isInRange(Vector2D coordinate) {
        if (coordinate.y >= dimension.y || coordinate.y < 0) {
            return false;
        }
        return coordinate.x < dimension.x && coordinate.x >= 0;
    }

    //Sets Tile within map. Throws exception if coordinate of range.
    public void setTile(Vector2D coordinate, char item) throws Exception {
        if (!isInRange(coordinate)) {
            throw new Exception("Tried to set item in invalid coordinates");
        }
        map[coordinate.y][coordinate.x] = item;
    }

    public char[][] getMap() {
        return this.map;
    }

    public Vector2D getDimension() {
        return this.dimension;
    }

    public boolean updateMap(Vector2D blockLocation, int tileSize) throws Exception {
        int y = blockLocation.y;
        int x = blockLocation.x;
        if (isInRange(blockLocation)) {
            if (y < getDimension().y - tileSize) {
                setTile(blockLocation, FileManager.BLOCK);

                if (y >= tileSize) {
                    return map[y + tileSize][x] != FileManager.BLOCK;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
