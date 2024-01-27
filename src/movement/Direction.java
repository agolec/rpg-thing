package movement;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;
    public static Direction fromString(String directionString){
        switch(directionString.toUpperCase()){
            case "UP":
                return UP;
            case "DOWN":
                return DOWN;
            case "LEFT":
                return LEFT;
            case "RIGHT":
                return RIGHT;
            default:
                throw new IllegalArgumentException("Invalid direction string entered: \'" + directionString + "\'");
        }
    }
}
