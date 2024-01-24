package map;

import entity.Entity;

import java.util.*;

public class Map {
    private static final int BORDER_SIZE = 2;
    private char[][] grid;
    private int mapRows;
    private int mapColumns;

    private Entity[][] entitiesOnGrid;


    private final int GRID_BORDER_OFFSET = 2;
    private final int SPRITE_PLACEMENT_OFFSET = 1;
    public Map(int rows,int columns,List<Entity> entities){
        this.mapRows = rows + BORDER_SIZE;
        this.mapColumns = columns + BORDER_SIZE;
        this.grid = new char[mapRows][mapColumns];
        initializeMap();
    }
    public Map(int rows,int columns){
        this.mapRows = rows + BORDER_SIZE;
        this.mapColumns = columns + BORDER_SIZE;
        this.grid = new char[mapRows][mapColumns];
        this.entitiesOnGrid = new Entity[mapRows][mapColumns];
        initializeMap();
    }

    private void initializeMap(){
        drawBorder();
        drawGrid();
    }
    private void drawBorder(){
        grid[0][0] = '┌';
        grid[0][this.mapColumns - 1] = '┐';

        for (int i = 1; i < this.mapColumns - 1; i++) {
            grid[0][i] = '─';
            grid[this.mapRows - 1][i] = '─';
        }

        grid[this.mapRows - 1][0] = '└';
        grid[mapRows - 1][this.mapColumns - 1] = '┘';

        for (int i = 1; i < mapRows - 1; i++) {
            grid[i][0] = '│';
            grid[i][this.mapColumns - 1] = '│';
        }
    }

    public void drawGrid(){
        final int GRID_ROWS = this.mapRows - 1;
        final int GRID_COLUMNS = this.mapColumns - 1;

        for(int i = 1; i < GRID_ROWS; i ++){
            for(int j = 1; j < GRID_COLUMNS; j++){
                grid[i][j] = '.';
            }
        }
    }
    public void printMap(){
        for(int rowIterator = 0; rowIterator < mapRows; rowIterator++){
            for(int columnIterator = 0; columnIterator < mapColumns; columnIterator++){
                System.out.print(grid[rowIterator][columnIterator]);
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
        grid[entity.getEntityColumnPosition()][entity.getEntityRowPosition()] = entity.getEntitySprite();
    }

    private boolean canPlaceEntityOnMap(Entity entityToPlace,int desiredRowPosition,int desiredColumnPosition) {
        final int MAX_GRID_ROW = this.mapRows - BORDER_SIZE;
        final int MAX_GRID_COLUMN = this.mapColumns - BORDER_SIZE;

        //use column and row position of the entity we are trying to place, to get the row and column we should check on the grid.
        int existingEntityColumn = entityToPlace.getEntityColumnPosition();
        int existingEntityRow = entityToPlace.getEntityRowPosition();
        if(desiredRowPosition < 1 || desiredRowPosition > MAX_GRID_ROW){
            return false;
        } else if (desiredColumnPosition < 1 || desiredColumnPosition > MAX_GRID_COLUMN){
            return false;
        }

        if(entitiesOnGrid[existingEntityColumn][existingEntityRow] != null){
            //if an entity exists on the entitiesOnGrid at the position where
            if(entitiesOnGrid[existingEntityColumn][existingEntityRow].canEntityCollide() || entityToPlace.canEntityCollide()){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }

    private void addEntity(Entity entityToPlace) {
        this.entitiesOnGrid[entityToPlace.getEntityColumnPosition()][entityToPlace.getEntityRowPosition()] = entityToPlace;
    }
    public void printEntities(){
        System.out.println("Entities are at:");
        for(int i = 0; i < this.entitiesOnGrid.length;i++){
            for(int j = 0; j < this.entitiesOnGrid[i].length;j++){
                if(entitiesOnGrid[i][j] == null){
                    System.out.print(".");
                } else {
                    System.out.print(this.entitiesOnGrid[i][j].getEntitySprite());
                }

            }
            System.out.println();
        }
    }
}
