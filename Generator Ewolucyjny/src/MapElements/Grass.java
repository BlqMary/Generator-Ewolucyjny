package MapElements;

import Misc.Vector2d;

public class Grass implements IMapElement{
    private Vector2d position;
    private int energy;

    public Grass(Vector2d position, int energy){
        this.position = position;
        this.energy = energy;
    }

    @Override
    public Vector2d getPosition(){
        return this.position;
    }
    @Override
    public int getEnergy(){
        return this.energy;
    }
    @Override
    public String toString(){
        return "\uD83C\uDF3B";
    }
}

