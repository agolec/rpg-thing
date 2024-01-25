package map;

import entity.Entity;
import entity.PlayerCharacter;

import java.util.Scanner;

public class Movement {
    static final String UP = "UP";
    static final String DOWN = "DOWN";
    static final String LEFT = "LEFT";
    static final String RIGHT = "RIGHT";
    private Scanner scan;
    private String currentChoice;
    private static String direction;
    public Movement(Scanner scan){
        this.scan = scan;
    }
    public void printMenu(){
        final String templateFormat = "Type \"%s\" to move %s";
        System.out.println("===Enter your movement===");
        System.out.println(String.format(templateFormat,UP,UP));
        System.out.println(String.format(templateFormat,DOWN,DOWN));
        System.out.println(String.format(templateFormat,LEFT,LEFT));
        System.out.println(String.format(templateFormat,RIGHT,RIGHT));
    }

    public void setDirection(){
        boolean isValidDirection = false;

        do {
            printMenu();
            String userInput = scan.nextLine().toUpperCase().trim();

            if (validDirection(userInput)) {
                this.direction = userInput;
                isValidDirection = true;
            } else {
                System.out.println("Invalid direction. Please enter a valid direction.");
            }
        } while (!isValidDirection);
    }
    private void clearInputBuffer(){
        while(scan.hasNextLine() && !scan.nextLine().isEmpty()){
            scan.next();
            //scan.nextLine();
        }
    }

    private boolean validDirection(String direction){
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
    public static void move(Entity entity,Map gameMap){
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
    public String toString(){
        return "Choice is + " + this.direction;
    }
}
