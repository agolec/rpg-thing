import entity.Door;
import entity.PlayerCharacter;
import map.Map;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        final int MAP_COL = 6*3;
        final int MAP_ROW = 4;

        HashMap<String,Map> maps = new HashMap<String,Map>();
        PlayerCharacter player = new PlayerCharacter("Player",1,'P',1,1,false);
        Door aDoor = new Door("Enterance Gate",'D',1,1);
        Door anotherDoor = new Door("House Door", 'H',MAP_ROW,MAP_COL);
        Map rpgMap = new Map(MAP_ROW,MAP_COL);
        String theTestMap = "rpgMap";
        maps.put(theTestMap,rpgMap);

        maps.get(theTestMap).placeEntity(aDoor, aDoor.getEntityColumnPosition(),aDoor.getEntityRowPosition());

//        maps.get("rpgMap").placeEntity(aDoor,aDoor.getEntityColumnPosition(), aDoor.getEntityRowPosition());
        maps.get(theTestMap).placeEntity(anotherDoor, anotherDoor.getEntityColumnPosition(), anotherDoor.getEntityRowPosition());
        maps.get(theTestMap).placeEntity(player,player.getEntityColumnPosition(), player.getEntityRowPosition());
        maps.get(theTestMap).printMap();
        maps.get(theTestMap).printEntities();

    }
}