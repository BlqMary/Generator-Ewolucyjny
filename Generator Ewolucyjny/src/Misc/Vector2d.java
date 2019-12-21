package Misc;

public class Vector2d {

    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return String.format("(%d,%d)",this.x,this.y);
        // return "(" + this.x + "," + this.y + ")";
    }

    public boolean precedes(Vector2d other){
        return (this.x <= other.x && this.y <= other.y);
    }

    public boolean follows(Vector2d other){
        return (this.x >= other.x && this.y >= other.y);
    }

    public Vector2d upperRight(Vector2d other){
        int upperX = Math.max(this.x,other.x);
        int upperY = Math.max(this.y,other.y);

        return new Vector2d(upperX,upperY);
    }

    public Vector2d lowerLeft(Vector2d other){
        int lowerX = Math.min(this.x,other.x);
        int lowerY = Math.min(this.y,other.y);
        return new Vector2d(lowerX,lowerY);
    }

    public boolean between(Vector2d lower, Vector2d upper){
        Vector2d current = new Vector2d(this.x,this.y);
        return (current.follows(lower) && current.precedes(upper));
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);
    }

    @Override
    public boolean equals(Object other) {
        if(other.getClass() == Vector2d.class){
            Vector2d vector2d = (Vector2d)other; //get class sprawdzic czy vector
            return (this.x == vector2d.x && this.y == vector2d.y);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int hashCode(){
        int hash = 117;
        hash += x * 37;
        hash += y * 187;
        return hash;
    }


    public Vector2d opposite(){
        return new Vector2d((-1)*this.x, (-1)*this.y);
    }
}

