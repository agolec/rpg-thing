package entity;

import entity.base.Entity;
import overworld.map.Map;
import overworld.map.Position;

public class Door extends Entity {

    private final String destinationMapId;
    private Position destinationPosition;

    public Door(String name, char sprite, Position doorPosition,String destinationMapId, Position destinationPosition){

        super(name,sprite,doorPosition,false);
        setPosition(destinationPosition);
        this.destinationMapId = destinationMapId;
    }

    private void setPosition(Position destinationPosition) {
        if(destinationPosition == null){
            throw new IllegalArgumentException("Error. Destination Position cannot be null.");
        }
        this.destinationPosition = destinationPosition;
    }

    @Override
    public void onEnter(Entity entity){
        System.out.println("entering " + destinationMapId);
    }
    public String getDestinationMapId() {
        return destinationMapId;
    }

    public int getDestinationRow() {
        return this.destinationPosition.getRow();
    }

    public int getDestinationColumn() {
        return this.destinationPosition.getColumn();
    }
    public String getDestination(){
        return this.destinationMapId;
    }

}
