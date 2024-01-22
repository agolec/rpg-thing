package map;

import entity.Entity;

import java.util.*;

public class Map {
    private static final int BORDER_SIZE = 2;
    private char[][] grid;
    private int mapRows;
    private int mapColumns;
    private List<Entity> existingEntities;

    private final int GRID_BORDER_OFFSET = 2;
    private final int SPRITE_PLACEMENT_OFFSET = 1;
    public Map(int rows,int columns,List<Entity> entities){
        this.mapRows = rows + BORDER_SIZE;
        this.mapColumns = columns + BORDER_SIZE;
        this.grid = new char[mapRows][mapColumns];
        this.existingEntities = new ArrayList<Entity>(entities);
        initializeMap();
    }
    public Map(int rows,int columns){
        this.mapRows = rows + BORDER_SIZE;
        this.mapColumns = columns + BORDER_SIZE;
        this.grid = new char[mapRows][mapColumns];
        this.existingEntities = new ArrayList<Entity>();
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
        final int MAX_GRID_ROW = this.mapRows - 1;
        final int MAX_GRID_COLUMN = this.mapColumns - 1;
        if(desiredRowPosition < 1 || desiredRowPosition > MAX_GRID_ROW - 1){
            return false;
        } else if (desiredColumnPosition < 1 || desiredColumnPosition > MAX_GRID_COLUMN - 1){
            return false;
        }
        return true;
    }

    private void addEntity(Entity entityToPlace) {
        this.existingEntities.add(entityToPlace);
    }
}
