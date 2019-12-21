package Misc;

import MapElements.Animal;

import java.util.Arrays;
import java.util.Random;

public class Gene {
    private int [] genes;

    //calkowicie nowe geny
    public Gene(){
        this.genes = new int[32];
        this.genes = randomGenes();
    }

    //generowanie genotypu z juz istniejacych genow
    public Gene(Animal mother, Animal father){
        this.genes = new int[32];
        this.genes = parentGenes(mother.getGenes().getGenes(),father.getGenes().getGenes()); //XD
    };

    //generowanie przypadkowych genów dla startowych zwierząt
    private int[] randomGenes(){
        Random random = new Random();
        int result[] = new int[32];
        for(int i=0; i<32;i++){
            result[i] = random.nextInt(7);
        }
        checkGenes(0,result);
        return result;
    }

    //podzielenie genów rodziców na grupy i połączenie ich
    private int[] parentGenes(int[] motherGenes, int[] fatherGenes){
        //gdzie dzielimy geny
        int boundary1 = 1 + new Random().nextInt(29); //od 1 do 29 bo kazda czesc musi miec przynajmniej 1 gen
        int boundary2 = boundary1 + new Random().nextInt(30 - boundary1); //od boundary1 do 30 bo kazda czesc musi miec przynajmniej 1 gen

        //branie genow od mamy i taty
        int[] childGenes = new int[32];
        for(int i=0;i<boundary1;i++) childGenes[i] = motherGenes[i];
        for(int i=boundary1;i<boundary2;i++) childGenes[i] = fatherGenes[i];
        for(int i=boundary2;i<32;i++)childGenes[i] = motherGenes[i];
        checkGenes(0,childGenes);
        Arrays.sort(childGenes);
        return childGenes;
    }

    //super funkcja rekurencyjna sprawdzajaca poprawnosc genów
    static private boolean checkGenes(int i, int[] genes){ //pierwsze wywołanie checkGenes(0,genes)
        if(i == 8) return true; //warunek wyjścia
        for(int gene:genes){ //sprawdzamy czy i znajduje sie w tablicy
            if(gene == i) return (checkGenes(i+1,genes)); //jesli tak to idziemy dalej
        }
        genes[new Random().nextInt(31)] = i; //jesli nie to losujemy miejsce dla tego genu
        return(checkGenes(1,genes));  //jak zmienilismy to musimy sprawdzic od poczatku czy jakas wartosc nam nie zniknela
    }

    public int getRandomGene(){
        return this.genes[new Random().nextInt(32)];
    }

    public int[] getGenes(){
        return this.genes;
    }

}
