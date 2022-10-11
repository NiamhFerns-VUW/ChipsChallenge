package nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chip;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import org.junit.jupiter.api.Test;

class DefaultCustomMonsterProviderTest {

    @Test
    void interactBefore() {
        DefaultCustomMonsterProvider customMonsterProvider = new DefaultCustomMonsterProvider();
        assertFalse(customMonsterProvider.interactBefore(null,null,null));
    }

    @Test
    void interactAfter() {
        DefaultCustomMonsterProvider customMonsterProvider = new DefaultCustomMonsterProvider();
        assertFalse(customMonsterProvider.interactAfter(null,null,null));
    }

    @Test
    void drawHierarchy() {
        assertEquals(0, new DefaultCustomMonsterProvider().drawHierarchy());
    }

    @Test
    void getAssociatedLevelPath() {
        DefaultCustomMonsterProvider customMonsterProvider = new DefaultCustomMonsterProvider();
        assertEquals(customMonsterProvider.getAssociatedLevelPath(),
            Path.of("./levels/level2.xml"));
    }

    @Test
    void getDirectionPathMap() {
        DefaultCustomMonsterProvider customMonsterProvider = new DefaultCustomMonsterProvider();
        assertNull(customMonsterProvider.getDirectionPathMap());
    }

    @Test
    void getDirectionList() {
        assertEquals(new DefaultCustomMonsterProvider().getDirectionList(), List.of(Direction.Up,Direction.Up,Direction.Up,Direction.Down,Direction.Down,Direction.Down));
    }
}