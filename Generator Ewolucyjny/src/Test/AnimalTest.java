import Map.Map;
import MapElements.Animal;
import MapElements.Grass;
import Misc.Gene;
import Misc.Vector2d;
import org.junit.Assert;
import org.junit.Test;


public class AnimalTest {
    Map map = new Map(10,10,1,10);
    Gene genes = new Gene();
    Vector2d position = new Vector2d(1,1);
    Animal animal = new Animal(map,position,10,genes);

    @Test
    public void animalEatTest(){
        Grass grass = new Grass(position,10);

        Assert.assertEquals(10,animal.getEnergy());

        animal.eat(grass,1);
        Assert.assertEquals(20,animal.getEnergy());

        animal.eat(grass,2);
        Assert.assertEquals(25,animal.getEnergy());

        animal.eat(grass,3);
        Assert.assertEquals(28,animal.getEnergy());

        animal.eat(grass,4);
        Assert.assertEquals(30,animal.getEnergy());

        animal.eat(grass,5);
        Assert.assertEquals(32,animal.getEnergy());
    }

    @Test
    public void animalReproduceTest(){
        Animal mother = new Animal(map,position,10,genes);
        Animal father = new Animal(map,position,10,genes);
        Animal child = Animal.reproduce(mother,father,10);
        Assert.assertNotEquals(null,child);
        Assert.assertEquals(5,child.getEnergy());
        Assert.assertEquals(7,mother.getEnergy());
        Assert.assertEquals(7,mother.getEnergy());
        Assert.assertNotEquals(null,child.getPosition());
        Assert.assertNotEquals(null,child.getGenes());
        Assert.assertEquals(null,Animal.reproduce(mother,father,40)); //maja za mało energii
    }

    @Test
    public void loseEnergyTest(){
        animal.move(5);
        Assert.assertEquals(5,animal.getEnergy());
        animal.move(1);
        Assert.assertEquals(4,animal.getEnergy());
    }

}
