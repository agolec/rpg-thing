package movement;

import entity.Entity;
import entity.PlayerCharacter;
import input.MovementUserInputHandler;
import map.Map;

public class Movement {
    static final String UP = "UP";
    static final String DOWN = "DOWN";
    static final String LEFT = "LEFT";
    static final String RIGHT = "RIGHT";
    private String direction;
    private MovementUserInputHandler userInputHandler;
    public Movement(MovementUserInputHandler userInputHandler){
        this.userInputHandler = userInputHandler;
    }
    public void setDirection(){
        this.direction = userInputHandler.getDirectionInput();
    }

    public static boolean validDirection(String direction){
        if(direction != null && !direction.isEmpty()){
            if(direction.equals(UP) || direction.equals(DOWN)
                    || direction.equals(LEFT) || direction.equals(RIGHT)){
                return true;
            } else {
                return false;
            }
        }
        return false;

    }
    public void move(Entity entity, Map gameMap){
        if(entity instanceof PlayerCharacter){
            if(direction == UP){
                entity.setRowPosition(entity.getMovementSpeed() * -1);
            } else if (direction == DOWN){
                entity.setRowPosition(entity.getMovementSpeed() * +1);
            } else if (direction == LEFT){
                entity.setColumnPosition(entity.getMovementSpeed() * -1);
            } else if(direction == RIGHT){
                entity.setColumnPosition(entity.getMovementSpeed() * +1);
            } else {
                System.out.println("Invalid direction. Enter direction again.");
            }
        }
    }
    public static String getUpString(){
        return UP;
    }
    public static String getDownString(){
        return DOWN;
    }
    public static String getLeftString(){
        return LEFT;
    }
    public static String getRightString(){
        return RIGHT;
    }
    public String toString(){
        return "Direction is + " + this.direction;
    }
}
