package nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

public abstract class CustomMonster extends MovingEntity {

    /**
     *
     */
    public CustomMonster() {
    }

    /**
     *
     * @return
     */
    public abstract Path getAssociatedLevelPath();

    /**
     *
     * @return
     */
    public abstract Map<Direction,Path> getDirectionPathMap();

    /**
     *
     * @return
     */
    public abstract List<Direction> getDirectionList();

}
