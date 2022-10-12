/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2.custom;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.CustomMovingEntityService;
import org.junit.jupiter.api.Test;

class CustomMovingEntityServiceTest {

    @Test
    void directionList() {
        CustomMovingEntityService entity = createEntity();
        assertEquals(entity.directionList(),List.of(Direction.Up,Direction.Up,Direction.Down,Direction.Down));
    }

    @Test
    void getNextDirection() {
        CustomMovingEntityService entity = createEntity();
        assertEquals(entity.getNextDirection(),Direction.Up);
        assertEquals(entity.getNextDirection(),Direction.Up);
        assertEquals(entity.getNextDirection(),Direction.Down);
        assertEquals(entity.getNextDirection(),Direction.Down);
        assertEquals(entity.getNextDirection(),Direction.Up);
        assertEquals(entity.getNextDirection(),Direction.Up);
        assertEquals(entity.getNextDirection(),Direction.Down);
        assertEquals(entity.getNextDirection(),Direction.Down);
    }
    public CustomMovingEntityService createEntity() {
        return new CustomMovingEntityService(
            Direction.None, new Coord(-1, -1)) {

            @Override
            public List<Direction> directionList() {
                return List.of(Direction.Up,Direction.Up,Direction.Down,Direction.Down);
            }

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
            public Image getImage() {
                return null;
            }
        };
    }
}