package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chip;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;

public class CustomMonsterProvider extends CustomMonster {

    private String customMonsterName;

    public CustomMonsterProvider(Direction facingDir, Coord coords) {
        this.setFacingDir(facingDir);
        this.coords = coords;
    }
    public CustomMonsterProvider(String customMonsterName) {
        this.customMonsterName = customMonsterName;
    }
    public CustomMonsterProvider() {
    }

    public String getCustomMonsterName() {
        return customMonsterName;
    }

    public void setCustomMonsterName(String customMonsterName) {
        this.customMonsterName = customMonsterName;
    }

    @Override
    public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
        return true;
    }

    @Override
    public boolean interactAfter(MovingEntity e, Direction d, Cell myCell) {
        if (e instanceof Chip) throw new Error("Chip is dead!  Killing not implemented yet.");
        return true;
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
        return List.of(Direction.Up,Direction.Up,Direction.Down,Direction.Down);
    }
}
