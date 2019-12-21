import Map.Map;
import Misc.Vector2d;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;


//w zwiazku z atrybutem protected kilka testow jest zakomentowanych
//testy były puszczane z pakietu do ktorego nalezy mapa

public class MapTest {
    Map map = new Map(10,10,2,1);
    Random random = new Random();

    @Test
    public void wrapTest(){
        Vector2d pos1 = new Vector2d(-10,2);
        Vector2d pos2 = new Vector2d(12,2);
        Vector2d pos3 = new Vector2d(2,-15);
        Vector2d pos4 = new Vector2d(2,30);
        Vector2d pos5 = new Vector2d(2,2);
        Vector2d pos6 = new Vector2d(-10,-10);
        Vector2d pos7 = new Vector2d(6,10);
        Vector2d pos8 = new Vector2d(5,6);

        Assert.assertEquals(new Vector2d(10,2),map.wrap(pos1));
        Assert.assertEquals(new Vector2d(0,2),map.wrap(pos2));
        Assert.assertEquals(new Vector2d(2,10),map.wrap(pos3));
        Assert.assertEquals(new Vector2d(2,0),map.wrap(pos4));
        Assert.assertEquals(new Vector2d(2,2),map.wrap(pos5));
        Assert.assertEquals(new Vector2d(10,10),map.wrap(pos6));
        Assert.assertEquals(new Vector2d(6,10),map.wrap(pos7));
        Assert.assertEquals(new Vector2d(5,6),map.wrap(pos8));
    }

//    @Test
//    public void growGrassTest1(){
//        map.growGrassSteppe(random);
//        map.growGrassJungle(random);
//        Assert.assertEquals(2,map.grass.size());
//        map.growGrassJungle(random);
//        map.growGrassJungle(random);
//        Assert.assertEquals(4,map.grass.size());
//        map.growGrassSteppe(random);
//        map.growGrassSteppe(random);
//        Assert.assertEquals(6,map.grass.size());
//        for(int i = 0; i<40; i++){
//            if(!map.isJungleFull())
//              map.growGrassJungle(random);
//        }
//        Assert.assertEquals(28,map.grass.size());
//    }
//
//    @Test
//    public void growGrassJungleTest(){
//        for(int i = 0; i <20 ;i++)
//            map.growGrassJungle(random);
//        Collection<Vector2d> grassPosition = map.grass.keySet();
//        for(Vector2d pos: grassPosition){
//            Grass grass = map.grass.get(pos);
//            Assert.assertEquals(true,grass.getPosition().between(map.getJungleStart(),map.getJungleEnd()));
//        }
//    }
//
//    @Test
//    public void growGrassSteppeTest(){
//        for(int i = 0; i <20 ;i++)
//            map.growGrassSteppe(random);
//        Collection<Vector2d> grassPosition = map.grass.keySet();
//        for(Vector2d pos: grassPosition){
//            Grass grass = map.grass.get(pos);
//            Assert.assertEquals(false,grass.getPosition().between(map.getJungleStart(),map.getJungleEnd()));
//        }
//    }

}
