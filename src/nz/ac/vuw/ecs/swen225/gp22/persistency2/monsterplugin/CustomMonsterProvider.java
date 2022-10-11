/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chip;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.LevelPath;

/**
 *
 */
public class CustomMonsterProvider extends CustomMonster {

    /**
     *
     * @param e - The Entity interacting with this Entity.
     * @param d - The direction the interacting Entity is moving.
     * @param myCell
     * @return
     */
    @Override
    public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
        return true;
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
        if (e instanceof Chip) throw new Error("Chip is dead!  Killing not implemented yet.");
        return true;
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
     *
     * @return
     */
    @Override
    public Path getAssociatedLevelPath() {
        return LevelPath.LEVEL2.getLevelPath();
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
        return List.of();
    }
}
