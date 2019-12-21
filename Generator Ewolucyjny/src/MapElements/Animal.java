package MapElements;

import Map.IWorldMap;
import Map.Map;
import Misc.Gene;
import Misc.IPositionChangeObserver;
import Misc.MapDirection;
import Misc.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Animal implements IMapElement {
    private Vector2d position;
    private int energy;
    private Gene genes;
    private MapDirection mapDirection;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<IPositionChangeObserver>();


    public Animal(IWorldMap map, Vector2d initialPosition,int initialEnergy, Gene genes){
        this.position = initialPosition;
        this.map = map;
        this.mapDirection = MapDirection.getRandomMapDirection();
        this.energy = initialEnergy;
        this.observers.add((IPositionChangeObserver) map);
        this.genes = genes;
    }

    public Animal(IWorldMap map,int initialEnergy){
        this.map = map;
        this.position = randomPosition();
        this.mapDirection = MapDirection.getRandomMapDirection();
        this.energy = initialEnergy;
        this.genes = new Gene();
        addObserver((IPositionChangeObserver) map);
    }

    //generowanie przypadkowych pozycji dla startowych zwierząt
    private Vector2d randomPosition(){
        Random random = new Random();
        int mapWidth = ((Map)map).getMapWidth();
        int mapHeight = ((Map)map).getMapHeight();
        return new Vector2d(random.nextInt(mapWidth),random.nextInt(mapHeight));
    }

    //jedzenie roślin
    public void eat(Grass grass, int numberOfAnimals){
        this.energy += grass.getEnergy()/numberOfAnimals;
    }

    //tracenie enrgii
    private void loseEnergy(int energy){
        this.energy -= energy;
    }

    //obracanie zwierzecia
    public void turn(){
        //obracamy sie tyle razy ile mowi gen
        int i = this.genes.getRandomGene();
        while(i > 0){
            mapDirection = mapDirection.next();
            i--;
        }
    }

    //poruszanie po mapie
    public void move(int moveEnergy)
    {
        Vector2d newPosition = map.wrap(position.add(mapDirection.toUnitVector()));
        Vector2d oldPosition = this.position;
        this.position = newPosition;
        positionChanged(oldPosition,newPosition);
        loseEnergy(moveEnergy);

    }

    public void die(Animal animal){
        if(animal.energy == 0){
            animal.removeObserver((IPositionChangeObserver) map);
            animal = null;
        }
    }

    //czy zwierzeta maja odpowiednio duzo energii by sie rozmnozyc
    public boolean canReproduce(int initialEnergy){ return (this.energy >= initialEnergy/2); }//moze sie rozmnażać powyzej połowy startowej energii

    //rozmnazanie
    static public Animal reproduce(Animal mother, Animal father, int initialEnergy){
        if(mother.canReproduce(initialEnergy) && father.canReproduce(initialEnergy)){
            Gene childGenes = new Gene(mother,father);
            Vector2d childPosition = findChildPosition(mother.getPosition(),mother.map);
            int childEnergy = childEnergy(mother,father);
            return new Animal(mother.map,childPosition,childEnergy,childGenes);
        }
        return null;
    }

    //wyliczenie energii dziecka
    static private int childEnergy(Animal mother, Animal father){
        int childEnergy = (mother.getEnergy() + father.getEnergy())/4; //dziecko dostaje 1/4 energii matki i rodzica
        mother.energy = mother.energy*3/4;
        father.energy = father.energy*3/4;
        return childEnergy;
    }

    //znalezienie pozycji dla dziecka
    static private Vector2d findChildPosition(Vector2d motherPosition, IWorldMap map){
        MapDirection childDirection = MapDirection.getRandomMapDirection(); //losujemy kierunek
        Vector2d childPosition = motherPosition.add(childDirection.toUnitVector()); // dziecko pojawia sie na polu koło rodziców w zależnosci od wylosowanego kierunku
        return childPosition;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }
    @Override
    public int getEnergy(){return this.energy;}

    public Gene getGenes(){
        return this.genes;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.position = newPosition;
        for(IPositionChangeObserver observer:observers){
            observer.positionChanged(this,oldPosition,newPosition);
        }
    }

    @Override
    public String toString() {
        return "\uD83D\uDC28";
    }
}
