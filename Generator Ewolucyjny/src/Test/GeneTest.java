import Map.Map;
import MapElements.Animal;
import Misc.Gene;
import Misc.Vector2d;
import org.junit.Assert;
import org.junit.Test;

public class GeneTest {

    Map map = new Map(10,10,1,10);
    Vector2d position = new Vector2d(1,1);
    Gene gene1 = new Gene();
    Gene gene2 = new Gene();
    Animal mother = new Animal(map,position,1,gene1);
    Animal father = new Animal(map,position,1,gene2);

    @Test
    public void geneTest(){
        Gene gene = new Gene();
        int []genes = gene.getGenes();
        boolean result = true;
        for(int i = 0; i < 8 ;i ++){
            boolean isIinGenes = false;
            for(int j = 0; j<32; j++){
                if(genes[j] == i)
                    isIinGenes = true;
            }
            if(!isIinGenes) result = false;
        }
        Assert.assertEquals(true,result);
    }

    @Test
    public void geneTest2(){
        Gene gene = new Gene(mother,father);
        int []genes = gene.getGenes();
        boolean result = true;
        for(int i = 0; i < 8 ;i ++){
            boolean isIinGenes = false;
            for(int j = 0; j<32; j++){
                if(genes[j] == i)
                    isIinGenes = true;
            }
            if(!isIinGenes) result = false;
        }
        Assert.assertEquals(true,result);
    }

}
