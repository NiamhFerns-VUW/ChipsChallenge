package nz.ac.vuw.ecs.swen225.gp22.persistency2.custom;

import java.util.Iterator;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

public abstract class CustomMovingEntityService extends MovingEntity {

    private Iterator<Direction> iterator;
    public CustomMovingEntityService(Direction facingDir, Coord coords) {
        super(facingDir, coords);
        this.iterator = directionList().listIterator();
    }
    public abstract List<Direction> directionList();

    public Direction getNextDirection() {
        assert !directionList().isEmpty();
        if (!iterator.hasNext()) {
            this.iterator = directionList().listIterator(0);
        }
        return iterator.next();
    }
}
