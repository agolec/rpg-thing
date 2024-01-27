package input;

import movement.Direction;
import movement.Movement;

import java.util.Scanner;

public class MovementUserInputHandler {
    private final Scanner scanner;

    public MovementUserInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public Direction getDirectionInput(){
        printMenu();
        return getValidDirectionInput();
    }


    private void printMenu() {
        final String UP = Movement.getUpString();
        final String DOWN = Movement.getDownString();
        final String LEFT = Movement.getLeftString();
        final String RIGHT = Movement.getRightString();

        final String templateFormat = "Type \"%s\" to move %s";
        System.out.println("===Enter your movement===");
        System.out.println(String.format(templateFormat, UP, UP));
        System.out.println(String.format(templateFormat, DOWN, DOWN));
        System.out.println(String.format(templateFormat, LEFT, LEFT));
        System.out.println(String.format(templateFormat, RIGHT, RIGHT));
    }

    private Direction getValidDirectionInput() {
        boolean isValidDirection = false;
        Direction direction = null;

        do {
            String userInput = scanner.nextLine().toUpperCase().trim();
            try {
                direction = Direction.fromString(userInput);
                isValidDirection = true;
            }catch(IllegalArgumentException e) {
                System.out.println("Invalid direction. Please enter another input.");
            }

        } while (!isValidDirection);

        return direction;
    }

}
