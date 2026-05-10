package entity;

import entity.base.Entity;
import overworld.map.Map;
import overworld.map.Position;

public class Door extends Entity {

    private final String destinationMapId;
    private final int destinationRow;
    private final int destinationColumn;
    public Door(String name, char sprite, Position position,String destinationMapId, int destinationRow,int destinationColumn){

        super(name,sprite,position,false);
        this.destinationMapId = destinationMapId;
        this.destinationRow = destinationRow;
        this.destinationColumn = destinationColumn;
    }
    @Override
    public void onEnter(Entity entity){
        System.out.println("entering " + destinationMapId);
    }
    public String getDestinationMapId() {
        return destinationMapId;
    }

    public int getDestinationRow() {
        return destinationRow;
    }

    public int getDestinationColumn() {
        return destinationColumn;
    }
    public String getDestination(){
        return this.destinationMapId;
    }

}
