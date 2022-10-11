/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

public class DefaultCustomMonsterProvider extends CustomMonster {

    /**
     *
     * @param e - The Entity interacting with this Entity.
     * @param d - The direction the interacting Entity is moving.
     * @param myCell
     * @return
     */
    @Override
    public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
        return false;
    }

    /**
     *
     * @param e - The Entity interacting with this Entity.
     * @param d - The direction the interacting Entity is moving.
     * @param myCell
     * @return
     */
    @Override
    public boolean interactAfter(MovingEntity e, Direction d, Cell myCell) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int drawHierarchy() {
        return 0;
    }

    /**
     * Returns path of xml file this entity belongs in.
     * @return
     */
    @Override
    public Path getAssociatedLevelPath() {
        return Path.of("./levels/level2.xml");
    }

    /**
     *
     * @return
     */
    @Override
    public Map<Direction, Path> getDirectionPathMap() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Direction> getDirectionList() {
        return List.of(Direction.Up,Direction.Up,Direction.Up,Direction.Down,Direction.Down,Direction.Down);
    }
}
