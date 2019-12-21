package Misc;

import java.util.Random;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST, NORTHEAST,SOUTHEAST, SOUTHWEST, NORTHWEST;

    @Override
    public String toString(){
        switch (this){
            case EAST: return "EAST";
            case WEST: return "WEST";
            case NORTH: return  "NORTH";
            case SOUTH: return "SOUTH";
            case NORTHEAST: return "NORTHEAST";
            case SOUTHEAST: return "SOUTHEAST";
            case NORTHWEST: return "NORTHWEST";
            case SOUTHWEST: return "SOUTHWEST";
            default: return null;
        }
    }

    public MapDirection next() {
        switch (this) {
            case EAST: return SOUTHEAST;
            case SOUTHEAST: return SOUTH;
            case SOUTH: return SOUTHWEST;
            case SOUTHWEST: return WEST;
            case WEST: return NORTHWEST;
            case NORTHWEST: return NORTH;
            case NORTH: return NORTHEAST;
            case NORTHEAST: return EAST;
            default: throw new IllegalArgumentException("Direction does not exist");
        }
    }

    public Vector2d toUnitVector(){
        switch (this){
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case EAST: return new Vector2d(1,0);
            case WEST: return new Vector2d(-1,0);
            case SOUTHEAST: return new Vector2d(1,-1);
            case SOUTHWEST: return new Vector2d(-1,-1);
            case NORTHWEST: return new Vector2d(-1,1);
            case NORTHEAST: return new Vector2d(1,1);
            default: throw new IllegalArgumentException("Direction does not exist");
        }
    }

    static public MapDirection getRandomMapDirection(){
        return values()[new Random().nextInt(values().length)];
    }
}
