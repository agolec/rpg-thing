package overworld.movement;

import entity.base.Entity;

public class MoveResult {
    private boolean moved;
    private Entity encountered;
    private Entity interactedEntity;

    public MoveResult(boolean moved, Entity encountered,Entity interactedEntity) {
        this.moved = moved;
        this.encountered = encountered;
        this.interactedEntity = interactedEntity;
    }

    public boolean didMove() {
        return moved;
    }

    public Entity getEncountered() {
        return encountered;
    }
    public Entity getInteractedEntity(){
        return this.interactedEntity;
    }
}
