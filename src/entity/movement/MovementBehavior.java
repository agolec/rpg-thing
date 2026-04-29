package entity.movement;

import entity.Entity;
import map.Map;

public interface MovementBehavior {
    void move(Entity entity, Map map);
}
