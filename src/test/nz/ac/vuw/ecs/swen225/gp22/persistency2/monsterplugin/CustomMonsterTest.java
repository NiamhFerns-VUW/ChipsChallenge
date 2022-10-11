package nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;
import org.junit.jupiter.api.Test;

class CustomMonsterTest {

    @Test
    void getAssociatedLevelPath() {
        CustomMonster customMonster = getCustomMonster();
        boolean equals = customMonster.getDirectionPathMap().equals(new HashMap<>());
        assertTrue(equals);
    }

    @Test
    void getDirectionPathMap() {
        CustomMonster customMonster = getCustomMonster();
        assertEquals(customMonster.getDirectionList(),List.of());
    }

    @Test
    void getDirectionList() {
        CustomMonster customMonster = getCustomMonster();
        assertEquals(customMonster.getDirectionList(),List.of());
    }

    public CustomMonster getCustomMonster() {
        CustomMonster customMonster = new CustomMonster() {
            @Override
            public Path getAssociatedLevelPath() {
                return Path.of("./");
            }

            @Override
            public Map<Direction, Path> getDirectionPathMap() {
                return new HashMap<>();
            }

            @Override
            public List<Direction> getDirectionList() {
                return List.of();
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
        };
        return customMonster;
    }
}