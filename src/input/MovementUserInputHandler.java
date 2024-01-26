package input;

import movement.Movement;

import java.util.Scanner;

public class MovementUserInputHandler {
    private final Scanner scanner;

    public MovementUserInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getDirectionInput() {
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

    private String getValidDirectionInput() {
        boolean isValidDirection = false;
        String direction = "";

        do {
            String userInput = scanner.nextLine().toUpperCase().trim();

            if (Movement.validDirection(userInput)) {
                direction = userInput;
                isValidDirection = true;
            } else {
                System.out.println("Invalid direction. Please enter a valid direction.");
            }
        } while (!isValidDirection);

        return direction;
    }
//    private void clearInputBuffer(){
//        while(scan.hasNextLine() && !scan.nextLine().isEmpty()){
//            scan.next();
//            //scan.nextLine();
//        }
//    }
}
