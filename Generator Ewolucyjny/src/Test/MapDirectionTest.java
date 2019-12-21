import Misc.MapDirection;
import org.junit.Assert;
import org.junit.Test;

public class MapDirectionTest {
    @Test
    public void mapDirectionNextTest(){
        Assert.assertEquals(MapDirection.NORTHEAST,MapDirection.NORTH.next());
        Assert.assertEquals(MapDirection.EAST,MapDirection.NORTHEAST.next());
        Assert.assertEquals(MapDirection.SOUTHEAST,MapDirection.EAST.next());
        Assert.assertEquals(MapDirection.SOUTH,MapDirection.SOUTHEAST.next());
        Assert.assertEquals(MapDirection.SOUTHWEST,MapDirection.SOUTH.next());
        Assert.assertEquals(MapDirection.WEST,MapDirection.SOUTHWEST.next());
        Assert.assertEquals(MapDirection.NORTHWEST, MapDirection.WEST.next());
        Assert.assertEquals(MapDirection.NORTH,MapDirection.NORTHWEST.next());
    }
}
