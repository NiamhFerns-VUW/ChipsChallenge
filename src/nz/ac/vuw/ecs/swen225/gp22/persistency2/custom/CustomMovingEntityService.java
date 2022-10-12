/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2.custom;

import java.util.Iterator;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

/**
 * Abstract class Service Providers need to implement.
 */
public abstract class CustomMovingEntityService extends MovingEntity {

    private Iterator<Direction> iterator;

    /**
     *
     * @param facingDir direction entity is facing.
     * @param coords coordinates of the moving entity.
     */
    public CustomMovingEntityService(Direction facingDir, Coord coords) {
        super(facingDir, coords);
        this.iterator = directionList().listIterator();
    }

    public CustomMovingEntityService() {
        super(Direction.None,new Coord(-1,-1));
    }
    /**
     * Houses the directions the custom moving entity will go.
     * @return
     */
    public abstract List<Direction> directionList();

    /**
     * Used to get the next direction for the custom moving entity from the direction list,
     * looping if at the end of list.
     * @return next direction the entity should go.
     */
    public Direction getNextDirection() {
        assert !directionList().isEmpty();
        if (!iterator.hasNext()) {
            this.iterator = directionList().listIterator(0);
        }
        return iterator.next();
    }
}
