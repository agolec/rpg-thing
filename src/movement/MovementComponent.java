package movement;

public class MovementComponent {
    private final int SPEED;

    public MovementComponent(int speed){
        this.SPEED = speed;
    }
    public int getSpeed(){
        return this.SPEED;
    }
}
