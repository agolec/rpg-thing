package map;

import entity.Entity;

import java.util.*;

public class Map {
    private char[][] grid;
    private int mapRows;
    private int mapColumns;
    private List<Entity> existingEntities;
    final int TOP_ROW = 0;
    final int LEFT_COLUMN = 0;
    int RIGHT_COLUMN;
    int BOTTOM_ROW;
    private final int GRID_BORDER_OFFSET = 2;
    private final int SPRITE_PLACEMENT_OFFSET = 1;
//    public Map(int rows,int columns,List<Entity> entities){
//        this.mapRows = rows + GRID_OFFSET;
//        this.mapColumns = columns + GRID_OFFSET;
//        this.RIGHT_COLUMN = mapColumns - 1;
//        this.BOTTOM_ROW = mapRows - 1;
//        this.grid = new char[mapRows][mapColumns];
//        this.existingEntities = new ArrayList<Entity>(entities);
//        initializeMap();
//    }
    public Map(int rows,int columns){
        this.mapRows = rows + GRID_BORDER_OFFSET;
        this.mapColumns = columns + GRID_BORDER_OFFSET;
        this.RIGHT_COLUMN = mapColumns - 1;
        this.BOTTOM_ROW = mapRows - 1;
        this.grid = new char[mapRows][mapColumns];
        this.existingEntities = new ArrayList<Entity>();
        initializeMap();
    }

    private void initializeMap(){
        for(int i = 0; i < mapRows; i++){
            for(int j = 0; j < mapColumns; j++){
                grid[i][j] = setGrid(i,j);
            }
        }
    }
    private char setGrid(int row, int column){

        if(row == TOP_ROW){
            if(column == LEFT_COLUMN){
                return '┌';
            } else if (column == RIGHT_COLUMN){
                return '┐';
            } else {
                return '─';
            }
        }
        else if (row == BOTTOM_ROW){
            if(column == LEFT_COLUMN){
                return '└';
            } else if (column == RIGHT_COLUMN){
                return '┘';
            } else {
                return '─';
            }
        }
        else {
            if (column == LEFT_COLUMN || column == RIGHT_COLUMN) {
                return '|';
            } else {
                return '.';
            }
        }
    }
    public void printMap(){
        for(int rowIterator = 0; rowIterator < mapRows; rowIterator++){
            int nextRow = rowIterator + 1;
            for(int columnIterator = 0; columnIterator < mapColumns; columnIterator++){
                System.out.print(grid[rowIterator][columnIterator] + " ");
            }
            System.out.println();
        }
    }
    public void placeEntity(Entity entity,int desiredColumnPosition,int desiredRowPosition) {

        // Place a character on the map at the specified position
        if (canPlaceEntityOnMap(entity,desiredColumnPosition,desiredRowPosition)) {
            System.out.println("yes we can place the character");
            addEntity(entity);
            placeSpriteOnGrid(entity);
        } else {
            System.out.println("Invalid position. Select another space.");
        }
    }

    private void placeSpriteOnGrid(Entity entity) {
        int entityRow;
        int entityColumn;
        System.out.println(entity);
        if(entity.getEntityRowPosition() + GRID_BORDER_OFFSET == this.mapColumns){
            entityColumn = entity.getEntityRowPosition();
        } else {
            entityColumn = entity.getEntityRowPosition() + SPRITE_PLACEMENT_OFFSET;
        }
        if(entity.getEntityColumnPosition() + GRID_BORDER_OFFSET == this.mapRows){
            entityRow = entity.getEntityColumnPosition();
        } else {
            entityRow = entity.getEntityColumnPosition() + SPRITE_PLACEMENT_OFFSET;
        }
        grid[entityRow][entityColumn] = entity.getEntitySprite();
    }

    private boolean canPlaceEntityOnMap(Entity entityToPlace,int desiredXPosition,int desiredYPosition) {
        // Check if the position is within the bounds of the map, and collision.

        //check the position of each entity on the map.
        //If the entity we are trying to place has the same position coordinates as an entity we are trying to place on the map,
        // check if the existing entity on the map can collide with the desired entity.

        //if existing entity is unable to collide, the position is invalid.
        int desiredXPositionRelativeToLeftColumn = desiredXPosition + SPRITE_PLACEMENT_OFFSET;
        int desiredXPositionRelativeToRightColumn = desiredXPosition - (2 * SPRITE_PLACEMENT_OFFSET);

        int desiredYPositionRelativeToTopRow = desiredYPosition + SPRITE_PLACEMENT_OFFSET;
        int desiredYPositionRelativeToBottomRow = desiredYPosition - (2 * SPRITE_PLACEMENT_OFFSET);

        if(desiredXPositionRelativeToLeftColumn <= this.LEFT_COLUMN || desiredXPositionRelativeToRightColumn  >= this.RIGHT_COLUMN
                || desiredYPositionRelativeToTopRow <= this.TOP_ROW || desiredYPositionRelativeToBottomRow >= this.BOTTOM_ROW){
            return false;
        }

        if(this.existingEntities != null){
            for(Entity existingEntity : existingEntities){
                if(existingEntity.entityHasDesiredPositionOfAnotherEntity(desiredXPosition,desiredYPosition)){
                    if(existingEntity.canEntityCollide()){
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {

        }
        return true;
    }

    private void addEntity(Entity entityToPlace) {
        this.existingEntities.add(entityToPlace);
    }
}
