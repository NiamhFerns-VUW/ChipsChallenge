package nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

public abstract class CustomMonster extends MovingEntity {

    public CustomMonster() {
    }
    public abstract Path getAssociatedLevelPath();
    public abstract Map<Direction,Path> getDirectionPathMap();
    public abstract List<Direction> getDirectionList();

}
