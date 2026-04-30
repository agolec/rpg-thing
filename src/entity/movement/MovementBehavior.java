package entity.movement;

import entity.base.Entity;
import overworld.map.Map;

public interface MovementBehavior {
    void move(Entity entity, Map map);
}
