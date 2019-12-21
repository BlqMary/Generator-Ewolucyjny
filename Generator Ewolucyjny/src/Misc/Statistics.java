package Misc;

import MapElements.Animal;

public class Statistics {
    private int simulationDays;
    private int plantsNumber;
    private int animalsAlive;
    private double avgLengthOfLife;
    private double avgEnergy;
    private int deadAnimals;
    private int[] genesCount;
    private int totalLivingDays;
    private int childCount;

    public Statistics(int animalsAlive, int startEnergy){
        this.simulationDays = 0;
        this.plantsNumber = 0;
        this.animalsAlive = animalsAlive;
        this.avgLengthOfLife = 0;
        this.avgEnergy = startEnergy;
        this.deadAnimals = 0;
        this.genesCount = new int[] {
            0,0,0,0,0,0,0,0
        };
        this.totalLivingDays = 0;
        this.childCount = 0;
    }

    public void addDay(){this.simulationDays++;}
    public void addAnimal(){this.animalsAlive++;}
    public void removeAnimal(){this.animalsAlive--; this.deadAnimals++;}
    public void setAvgEnergy(int totalEnergy){ this.avgEnergy = totalEnergy/this.animalsAlive; }
    public void addPlant(){this.plantsNumber++;}
    public void removePlant(){this.plantsNumber--;}
    public void addTotalLivingDays(){this.totalLivingDays++;}
    public void addChild(){this.childCount++;}

    public int getSimulationDays(){ return this.simulationDays; }

    public int getPlantsNumber(){ return this.plantsNumber; }

    public int getAnimalsAlive(){ return this.animalsAlive; }

    public double getAvgChildCount(){ return (double)this.childCount/(this.animalsAlive + this.deadAnimals);}

    public double getAvgLengthOfLife(){
        this.avgLengthOfLife = this.totalLivingDays /(this.animalsAlive + this.deadAnimals);
        return this.avgLengthOfLife;
    }

    public double getAvgEnergy() { return this.avgEnergy; }

    public void addToGenesPool(Animal animal){
        int[] genes = animal.getGenes().getGenes(); //pierwszy getter daje typ GENE, drugi daje tablice intów
        for(int gene:genes){
            this.genesCount[gene]++;
        }
    }

    public int dominantGene(){
        int dominantGene = 0;
        int dominantGeneCount = 0;
        for(int i = 0 ; i < this.genesCount.length; i++){
            if(this.genesCount[i] > dominantGeneCount){
                dominantGene = i;
                dominantGeneCount = this.genesCount[i];
            }
        }
        return dominantGene;
    }
}
