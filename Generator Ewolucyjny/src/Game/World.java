package Game;

import Map.Simulation;
import Misc.ParseJSON;

public class World {
    public static void main (String[] args)   {

        String dir = System.getProperty("user.dir") + "\\parameters.json";
        ParseJSON parser = new ParseJSON(dir);
        try {
            Simulation simulation = new Simulation(parser.getStartEnergy(),parser.getMoveEnergy(),parser.getMapHeight(),
                    parser.getMapWidth(),parser.getJungleRatio(),parser.getGrassEnergy(),parser.getAnimalsCount());
            Visualization visualization = new Visualization(simulation);
            visualization.startAnimation();

        } catch ( NullPointerException ex){
            System.out.println("Null pointer ex");
        };



    }

}
