import Misc.Vector2d;
import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest {

    @Test
    public void vector2dEqualsTest(){
        Vector2d vector2d1 = new Vector2d(1,2);
        Vector2d vector2d2 = new Vector2d(1,2);
        Vector2d vector2d3 = new Vector2d(2,3);
        Assert.assertTrue(vector2d1.equals(vector2d1));
        Assert.assertTrue(vector2d1.equals(vector2d2));
        Assert.assertTrue(vector2d2.equals(vector2d2));
        Assert.assertTrue(vector2d2.equals(vector2d1));
        Assert.assertFalse(vector2d1.equals(vector2d3));
        Assert.assertFalse(vector2d2.equals(vector2d3));
    }

    @Test
    public void vector2dtoStringTest(){
        Vector2d vector2d = new Vector2d(1,2);
        Assert.assertEquals("(1,2)",vector2d.toString());
    }

    @Test
    public void vector2dPrecedesTest(){
        Vector2d vector2d1 = new Vector2d(1,2);
        Vector2d vector2d2 = new Vector2d(2,3);
        Assert.assertTrue(vector2d1.precedes(vector2d2));
        Assert.assertFalse(vector2d2.precedes(vector2d1));
    }

    @Test
    public void vector2dFollowsTest(){
        Vector2d vector2d1 = new Vector2d(1,2);
        Vector2d vector2d2 = new Vector2d(2,3);
        Assert.assertTrue(vector2d2.follows(vector2d1));
        Assert.assertFalse(vector2d1.follows(vector2d2));
    }

    @Test
    public void vector2dUpperRightTest(){
        Vector2d vector2d1 = new Vector2d(1,2);
        Vector2d vector2d2 = new Vector2d(5,-1);
        Assert.assertEquals(new Vector2d(5,2),vector2d1.upperRight(vector2d2));
        Assert.assertEquals(new Vector2d(5,2),vector2d2.upperRight(vector2d1));
    }

    @Test
    public void vector2dLowerLeftTest(){
        Vector2d vector2d1 = new Vector2d(1,2);
        Vector2d vector2d2 = new Vector2d(5,-1);
        Assert.assertEquals(new Vector2d(1,-1),vector2d1.lowerLeft(vector2d2));
        Assert.assertEquals(new Vector2d(1,-1),vector2d2.lowerLeft(vector2d1));
    }

    @Test
    public void vector2dAddTest(){
        Vector2d vector2d1 = new Vector2d(1,2);
        Vector2d vector2d2 = new Vector2d(5,-1);
        Assert.assertEquals(new Vector2d(6,1),vector2d1.add(vector2d2));
        Assert.assertEquals(new Vector2d(6,1),vector2d2.add(vector2d1));
    }

    @Test
    public void vector2dSubtractTest(){
        Vector2d vector2d1 = new Vector2d(1,2);
        Vector2d vector2d2 = new Vector2d(5,-1);
        Assert.assertEquals(new Vector2d(-4,3),vector2d1.subtract(vector2d2));
        Assert.assertEquals(new Vector2d(4,-3),vector2d2.subtract(vector2d1));
    }

    @Test
    public void vector2dOppositeTest(){
        Vector2d vector2d = new Vector2d(1,2);
        Assert.assertEquals(new Vector2d(-1,-2),vector2d.opposite());
    }

    @Test
    public void vector2dBetweenTest(){
        Vector2d vector2d1 = new Vector2d(1,1);
        Vector2d vector2d2 = new Vector2d(1,2);
        Vector2d vector2d3 = new Vector2d(3,3);
        Assert.assertTrue(vector2d2.between(vector2d1,vector2d3));
        Assert.assertFalse(vector2d1.between(vector2d2,vector2d3));
        Assert.assertFalse(vector2d3.between(vector2d2,vector2d1));
    }
}
