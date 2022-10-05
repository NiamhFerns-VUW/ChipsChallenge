package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.nio.file.Path;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;

/**
 * interface for service providers to implement if they want a custom entity
 */
public interface CustomEntity extends Entity {

    /**
     *
     * @return
     */
    Direction getNextDirection();

    /**
     *
     * @return
     */
    Map<Direction, Path> getImageMap();

}
