package overworld.map;

import entity.base.Entity;
import entity.specialised.CombatEntity;
import overworld.movement.Direction;
import overworld.movement.MoveResult;
import util.Debug;

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
    public void printMap() {
        drawBorder();
        drawGrid(); // reset entire grid to clean state

        // redraw all entities from source of truth
        for (int row = 0; row < mapRows; row++) {
            for (int col = 0; col < mapColumns; col++) {
                if (entitiesOnGrid[row][col] != null) {
                    grid[row][col] = entitiesOnGrid[row][col].getSprite();
                }
            }
        }

        // now print
        for (int rowIterator = 0; rowIterator < mapRows; rowIterator++) {
            for (int columnIterator = 0; columnIterator < mapColumns; columnIterator++) {
                System.out.print(grid[rowIterator][columnIterator]);
            }
            System.out.println();
        }
    }
    public void placeEntity(Entity entity, int row, int col) {
        if (canPlaceEntityOnMap(entity, row, col)) {
            entity.getPosition().set(row, col);
            addEntity(entity);
            //grid[row][col] = entity.getSprite(); // inline, controlled
        }
    }
    public MoveResult moveEntity(Entity entity, Direction direction) {
        int desiredColumnPosition = calculateDesiredColumnPosition(entity, direction);
        int desiredRowPosition = calculateDesiredRowPosition(entity, direction);

        Entity existing = entitiesOnGrid[desiredRowPosition][desiredColumnPosition];

        if (existing != null) {
            existing.onEnter(entity);

            if (existing.canCollide()) {
                return new MoveResult(false, existing);
            }
        }

        if (canPlaceEntityOnMap(entity, desiredRowPosition, desiredColumnPosition)) {
            moveEntityToPosition(entity, desiredRowPosition, desiredColumnPosition);
            return new MoveResult(true, null); // only when movement actually happens
        } else {
            System.out.println("Invalid position. Select another space.");
            return new MoveResult(false, null); // correctly report failure
        }
    }

    private int calculateDesiredColumnPosition(Entity entity, Direction direction) {
        int col = entity.getPosition().getColumn();

        return switch (direction) {
            case LEFT -> col - 1;
            case RIGHT -> col + 1;
            default -> col;
        };
    }
    private int calculateDesiredRowPosition(Entity entity, Direction direction) {
        int row = entity.getPosition().getRow();

        return switch (direction) {
            case UP -> row - 1;
            case DOWN -> row + 1;
            default -> row;
        };
    }



    public void moveEntityToPosition(Entity entity, int targetRow, int targetColumn) {
        Position pos = entity.getPosition();

        int oldRow = pos.getRow();
        int oldCol = pos.getColumn();

        // 1a. Clear old position off entity grid
        entitiesOnGrid[oldRow][oldCol] = null;

        // 2. Update entity position
        pos.set(targetRow, targetColumn);

        // 3. Place in new position
        entitiesOnGrid[targetRow][targetColumn] = entity;
    }


//    private void placeSpriteOnGrid(Entity entity) {
//        Position pos = entity.getPosition();
//        grid[pos.getRow()][pos.getColumn()] = entity.getSprite();
//    }

    public boolean canPlaceEntityOnMap(Entity entity, int targetRow, int targetColumn) {
        final int MIN = 1;
        final int MAX_ROW = this.mapRows - BORDER_SIZE;
        final int MAX_COL = this.mapColumns - BORDER_SIZE;

        // 1. Bounds check
        if (targetRow < MIN || targetRow > MAX_ROW) return false;
        if (targetColumn < MIN || targetColumn > MAX_COL) return false;

        // 2. Check occupancy
        Entity existing = entitiesOnGrid[targetRow][targetColumn];

        if (existing == null) {
            return true;
        }

        // 3. Collision logic
        return !existing.canCollide() && !entity.canCollide();
    }


    private void addEntity(Entity entity) {
        Position pos = entity.getPosition();
        entitiesOnGrid[pos.getRow()][pos.getColumn()] = entity;
    }
    public void printEntities(){
        System.out.println("Entities are at:");
        for(int i = 0; i < this.entitiesOnGrid.length;i++){
            for(int j = 0; j < this.entitiesOnGrid[i].length;j++){
                if(entitiesOnGrid[i][j] == null){
                    System.out.print(".");
                } else {
                    System.out.print(this.entitiesOnGrid[i][j].getSprite());
                }

            }
            System.out.println();
        }
    }
    public int getRows(){
        return this.mapRows;
    }
    public int getColumns(){
        return this.mapColumns;
    }
    public char getTile(int row, int column){
        Entity entity = entitiesOnGrid[row][column];

        if(entity != null){
            return entity.getSprite();
        }
        return grid[row][column];
    }
    //for debugging
    public void debugPrintEntityLocations() {
        if(!Debug.ENABLED) {
            return;
        }

        System.out.println("=== ENTITY LOCATIONS ===");

        for (int r = 0; r < mapRows; r++) {
            for (int c = 0; c < mapColumns; c++) {

                if (entitiesOnGrid[r][c] != null) {
                    System.out.println("Entity at: " + r + "," + c);
                }
            }
        }
    }

    public void removeEntity(Entity entity) {
        Position pos = entity.getPosition();

        int row = pos.getRow();
        int col = pos.getColumn();

        if (entitiesOnGrid[row][col] == entity) {
            entitiesOnGrid[row][col] = null;
        }
    }
}
