package Map;

import MapElements.Animal;
import MapElements.Grass;
import Misc.IPositionChangeObserver;
import Misc.Vector2d;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.HashMap;
import java.util.Map;


abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, Grass> grass = new HashMap<>();
    protected ListMultimap<Vector2d, Animal> animals = ArrayListMultimap.create();


    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if(canMoveTo(animalPosition)){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (objectAt(position) != null);
    }

    public boolean isGrass(Vector2d position){
        return (objectAt(position) instanceof Grass);
    }

    public Object objectAt(Vector2d position) {
        if(grass.get(position) == null){
            return animals.get(position);
        }
        else {
            return grass.get(position);
        }
    }

    @Override
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition){
        animals.remove(oldPosition,animal);
        animals.put(newPosition,animal);
    }

}