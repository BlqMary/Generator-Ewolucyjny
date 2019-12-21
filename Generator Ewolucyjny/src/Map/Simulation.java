package Map;

import MapElements.Animal;
import Misc.Statistics;
import Misc.Vector2d;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static MapElements.Animal.reproduce;

public class Simulation {
    private Map map;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private int initialEnergy;
    private int moveEnergy;
    private Statistics statistics;


    public Simulation(int initialEnergy, int moveEnergy, int mapHeight, int mapWidth, int jungleRatio, int grassEnergy, int animalsCount){
        this.map = new Map(mapHeight,mapWidth,jungleRatio,grassEnergy);
        this.initialEnergy = initialEnergy;
        this.moveEnergy = moveEnergy;
        this.statistics = new Statistics(animalsCount,initialEnergy);

        spawnFirstAnimals(animalsCount);
    }

    private void spawnFirstAnimals(int animalsCount){
        int i = 0;
        while(i < animalsCount){
            Animal animal = new Animal(map,initialEnergy);
            if(map.place(animal)){
                i++;
                statistics.addToGenesPool(animal);
            }
        }
    }


    public void day() {
        removeDeadAnimals();
        turnAnimals();
        moveAnimals();
        animalsReproduce();
        animalsEat();
        growGrass();
        statistics.addDay();
    }

    private void growGrass(){
        Random random = new Random();
        if(!this.map.isSteppeFull()){
            this.map.growGrassSteppe(random);
            statistics.addPlant();
        }
        if(!this.map.isJungleFull()){
            this.map.growGrassJungle(random);
            statistics.addPlant();
        }
    }

    private void removeDeadAnimals(){
        Collection<Animal> animalCollection = map.animals.values();
        Animal[] animals = new Animal[animalCollection.size()];
        animals = animalCollection.toArray(animals);
        for(Animal animal:animals){
            if(animal.getEnergy() == 0){
                map.animals.remove(animal.getPosition(),animal);
                animal.die(animal);
                statistics.removeAnimal();
            }
        }
    }

    private void turnAnimals(){
        Collection<Animal> animals = map.animals.values();
        for(Animal animal:animals) animal.turn();
    }

    private void moveAnimals() {
        Collection<Animal> animalCollection = map.animals.values();
        Animal[] animals = new Animal[animalCollection.size()];
        int sumEnergy = 0;
        animals = animalCollection.toArray(animals);
        for (Animal animal : animals) {
            animal.move(moveEnergy);
            sumEnergy += animal.getEnergy();
            statistics.addTotalLivingDays();
        }
        statistics.setAvgEnergy(sumEnergy);
    }

    private void animalsEat(){
        Collection<Vector2d> positions = map.animals.keySet();

        for(Vector2d position: positions){
            Collection<Animal> animalsInPosition = map.animals.get(position);

            Animal[] animalsArray = new Animal[animalsInPosition.size()];
            animalsArray = animalsInPosition.toArray(animalsArray); //do tablicy
            Arrays.sort(animalsArray, (animal1, animal2) -> animal2.getEnergy()  - animal1.getEnergy()); //sortowanie malejace po energii

            int sameEnergyAnimalsCount = countAnimalsWithEqualEnergy(animalsArray);

            if(map.isGrass(position)){
                for(Animal animal: Arrays.copyOfRange(animalsArray,0,sameEnergyAnimalsCount-1))
                    if(animal != null)
                        animal.eat(map.grass.get(position),sameEnergyAnimalsCount);
                map.grass.remove(position);
                statistics.removePlant();
            }
        }
    }

    private void animalsReproduce(){
        Collection<Vector2d> positionsCollection = map.animals.keySet();
        Vector2d[] positions = new Vector2d[positionsCollection.size()];
        positions = positionsCollection.toArray(positions);

        for(Vector2d position: positions){
            Collection<Animal> animalsInPosition = map.animals.get(position);
            if(animalsInPosition.size() > 1){ //upewnienie sie ze jest wiecej niz 1 zwierze na tej pozycji
                Animal[] animals = new Animal[animalsInPosition.size()];
                animals = animalsInPosition.toArray(animals); //do tablicy
                Arrays.sort(animals, (animal1, animal2) -> animal2.getEnergy()  - animal1.getEnergy()); //sortowanie malejace po energii
                Animal mother = animals[0]; //najsilniejszy
                Animal father = animals[1]; //drugi najsilniejszy
                Animal child = reproduce(mother,father,initialEnergy);
                if(child != null) {
                    map.place(child);
                    statistics.addAnimal();
                    statistics.addChild();
                    statistics.addToGenesPool(child);
                }
            }
        }
    }

    public boolean areAnimalsAlive(){
        return(map.animals.values().size() > 0);
    }

    private int countAnimalsWithEqualEnergy(Animal [] animals){
        int result = 1;
        int maxEnergy = animals[0].getEnergy();
        for(int i = 0; i < animals.length && animals[i].getEnergy() == maxEnergy; i++){
            result++;
        }
        return result;
    }

    private String showStatistics(){
        String result = "Statystyki:\n";
        result += "Dzień: " + statistics.getSimulationDays() + "\n";
        result += "Ilość żywych zwierząt: " + statistics.getAnimalsAlive() + "\n";
        result += "Ilość roślin: " + statistics.getPlantsNumber() + "\n";
        result += "Srednia energia: " + df2.format(statistics.getAvgEnergy()) + "\n";
        result += "Srednia liczba dzieci: " + df2.format(statistics.getAvgChildCount()) + "\n";
        result += "Srednia długość życia: " + statistics.getAvgLengthOfLife() + "\n";
        result += "Dominujący gen: " + statistics.dominantGene() +  "\n";
        return result;

    }

    public Map getMap(){
        return this.map;
    }

    @Override
    public String toString(){
        return showStatistics() + this.map.toString();
    }
}
