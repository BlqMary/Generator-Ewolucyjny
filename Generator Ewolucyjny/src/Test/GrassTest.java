import MapElements.Grass;
import Misc.Vector2d;
import org.junit.Assert;
import org.junit.Test;

public class GrassTest {

    @Test
    public void grassTest(){
        Grass grass = new Grass(new Vector2d(1,1),10);
        Assert.assertEquals(10,grass.getEnergy());
        Assert.assertEquals(new Vector2d(1,1),grass.getPosition());
    }
}
