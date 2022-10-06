package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.nio.file.Path;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

public class CustomEntityProvider implements CustomMonster {

    @Override
    public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
        return false;
    }

    @Override
    public boolean interactAfter(MovingEntity e, Direction d, Cell myCell) {
        return false;
    }

    @Override
    public int drawHierarchy() {
        return 0;
    }

    @Override
    public Direction getNextDirection() {
        return null;
    }

    @Override
    public Map<Direction, Path> getImageMap() {
        return null;
    }
}
