package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

public abstract class CustomMonster extends MovingEntity {

    abstract Map<Direction,Path> getDirectionPathMap();
    abstract List<Direction> getDirectionList();

}
