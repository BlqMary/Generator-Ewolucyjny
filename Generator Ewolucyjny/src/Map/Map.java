package Map;

import MapElements.Grass;
import Misc.Vector2d;

import java.util.Collection;
import java.util.Random;

public class Map extends AbstractWorldMap{
    private Vector2d lowerLeft; //"brzegi" mapy
    private Vector2d upperRight;
    private Vector2d jungleStart; //gdzie jest dżungla
    private Vector2d jungleEnd;
    private int grassEnergy;
    MapVisualizer map = new MapVisualizer(this);

    public Map(int height, int width, int jungleRatio, int grassEnergy){
        int jungleWidth, jungleHeight;
        jungleHeight = jungleWidth = width/jungleRatio;
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width,height);
        int jungleStartX = (width-jungleWidth)/2+1;
        int jungleStartY = (height-jungleHeight)/2+1;
        this.jungleStart = new Vector2d(jungleStartX,jungleStartY);
        this.jungleEnd = new Vector2d(jungleStartX+jungleWidth-1, jungleStartY+jungleHeight-1);
        this.grassEnergy = grassEnergy;
    }

    public Vector2d wrap(Vector2d position){
        if(position.x > upperRight.x) position =  new Vector2d(lowerLeft.x,position.y);
        if(position.x < lowerLeft.x) position = new Vector2d(upperRight.x,position.y);
        if(position.y > upperRight.y) position = new Vector2d(position.x,lowerLeft.y);
        if(position.y < lowerLeft.y) position = new Vector2d(position.x,upperRight.y);
        return position;
    }

    protected void growGrassSteppe(Random random){
        Vector2d steppePosition;
        Object object;
        do {
            steppePosition = new Vector2d(random.nextInt(upperRight.x+1), random.nextInt(upperRight.y+1));
            object = objectAt(steppePosition);
        } while (steppePosition.between(jungleStart,jungleEnd) || object instanceof Grass ||
                (object instanceof Collection<?> && ((Collection) object).size() != 0)); //poki nie jest na stepie, jest tam trawa lub zwierzeta

        this.grass.put(steppePosition,new Grass(steppePosition,grassEnergy));
    }

    protected void growGrassJungle(Random random){
        int jungleWidth = jungleEnd.x-jungleStart.x;
        int jungleHeight = jungleEnd.y-jungleStart.y;
        int junglePositionX;
        int junglePositionY;
        Vector2d junglePosition;
        Object object;
        do{
            junglePositionX = jungleStart.x + random.nextInt(jungleWidth+1);
            junglePositionY = jungleStart.y + random.nextInt(jungleHeight+1);
            junglePosition = new Vector2d(junglePositionX,junglePositionY);
            object = objectAt(junglePosition);
        } while(object instanceof Grass || (object instanceof Collection<?> && ((Collection) object).size() != 0));

        this.grass.put(junglePosition,new Grass(junglePosition,grassEnergy));
    }

    protected boolean isJungleFull(){
        for(int i = jungleStart.x; i <= jungleEnd.x;i++){
            for(int j = jungleStart.y; j <= jungleEnd.y;j++) {
                Object object = objectAt(new Vector2d(i, j));
                if (object instanceof Collection<?> && ((Collection) object).size() == 0)
                return false;
            }
        }
        return true;
    }

    protected boolean isSteppeFull(){
        for(int i = lowerLeft.x; i <= upperRight.x;i++){
            for(int j = lowerLeft.y; j <= upperRight.y;j++) {
                Vector2d position = new Vector2d(i, j);
                if(!position.between(jungleStart,jungleEnd)){ //czy jesteśmy w steppie
                    Object object = objectAt(position);
                    if (object instanceof Collection<?> && ((Collection) object).size() == 0)
                        return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return map.draw(lowerLeft,upperRight);
    }

    public int getMapHeight(){
        return upperRight.y;
    }

    public int getMapWidth(){
        return upperRight.x;
    }

    public Vector2d getJungleStart(){return this.jungleStart;}
    public Vector2d getJungleEnd(){return this.jungleEnd;}
}
