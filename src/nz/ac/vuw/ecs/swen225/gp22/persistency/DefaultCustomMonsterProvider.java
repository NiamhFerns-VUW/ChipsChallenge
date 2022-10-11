package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

public class DefaultCustomMonsterProvider extends CustomMonster{

    private String name;

    public DefaultCustomMonsterProvider(String name) {
        this.name = name;
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
    Map<Direction, Path> getDirectionPathMap() {
        return null;
    }

    @Override
    List<Direction> getDirectionList() {
        return List.of(Direction.Up,Direction.Up,Direction.Up,Direction.Down,Direction.Down,Direction.Down);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}