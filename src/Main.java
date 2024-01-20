import entity.Door;
import map.Map;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        final int MAP_COL = 10;
        final int MAP_ROW = 6;

        HashMap<String,Map> maps = new HashMap<String,Map>();
        Door aDoor = new Door("Enterance Gate",'D',0,0);
        Door anotherDoor = new Door("House Door", 'H',MAP_ROW,MAP_COL);
        Map rpgMap = new Map(MAP_ROW,MAP_COL);
        maps.put("rpgMap",rpgMap);

        maps.get("rpgMap").placeEntity(aDoor, aDoor.getEntityColumnPosition(),aDoor.getEntityRowPosition());

        maps.get("rpgMap").placeEntity(aDoor,aDoor.getEntityColumnPosition(), aDoor.getEntityRowPosition());
        maps.get("rpgMap").placeEntity(anotherDoor, anotherDoor.getEntityColumnPosition(), anotherDoor.getEntityRowPosition());
        maps.get("rpgMap").printMap();

    }
}