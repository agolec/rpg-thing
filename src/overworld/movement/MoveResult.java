package overworld.movement;

import entity.base.Entity;

public class MoveResult {
    private boolean moved;
    private Entity encountered;

    public MoveResult(boolean moved, Entity encountered) {
        this.moved = moved;
        this.encountered = encountered;
    }

    public boolean didMove() {
        return moved;
    }

    public Entity getEncountered() {
        return encountered;
    }
}
