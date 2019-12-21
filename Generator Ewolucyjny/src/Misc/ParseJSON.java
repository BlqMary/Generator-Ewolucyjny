package Misc;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParseJSON {
    JSONParser parser;
    private int mapWidth ;
    private int mapHeight;
    private int startEnergy;
    private int moveEnergy;
    private int grassEnergy;
    private int jungleRatio;
    private int animalsCount;

    public ParseJSON(String file){
        this.parser = new JSONParser();
        try{
            Object object = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) object;
            mapWidth = ((Long) jsonObject.get("width")).intValue();
            mapHeight = ((Long) jsonObject.get("height")).intValue();
            startEnergy = ((Long) jsonObject.get("startEnergy")).intValue();
            moveEnergy = ((Long) jsonObject.get("moveEnergy")).intValue();
            grassEnergy = ((Long) jsonObject.get("grassEnergy")).intValue();
            jungleRatio = ((Long) jsonObject.get("jungleRatio")).intValue();
            animalsCount = ((Long) jsonObject.get("animalsCount")).intValue();
        }
        catch(ParseException | IOException ex){
            if(ex instanceof FileNotFoundException) System.out.println("File not found!");
            System.exit(0);
        }
    }

    public int getAnimalsCount() {
        return animalsCount;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getGrassEnergy() {
        return grassEnergy;
    }

    public int getJungleRatio() {
        return jungleRatio;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public int getStartEnergy() {
        return startEnergy;
    }
}

