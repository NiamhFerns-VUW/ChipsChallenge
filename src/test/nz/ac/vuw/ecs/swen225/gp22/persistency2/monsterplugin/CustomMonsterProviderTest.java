package nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.nio.file.Path;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chip;
import org.junit.jupiter.api.Test;

class CustomMonsterProviderTest {

    @Test
    void interactBefore() {
        CustomMonsterProvider customMonsterProvider = new CustomMonsterProvider();
        assertTrue(customMonsterProvider.interactBefore(null,null,null));
    }

    @Test
    void interactAfter() {
        CustomMonsterProvider customMonsterProvider = new CustomMonsterProvider();
        assertTrue(customMonsterProvider.interactAfter(null,null,null));
        assertThrows(Error.class,()->{
            customMonsterProvider.interactAfter(new Chip(),null,null);
        });
    }

    @Test
    void drawHierarchy() {
        assertEquals(0, new CustomMonsterProvider().drawHierarchy());
    }

    @Test
    void getAssociatedLevelPath() {
        CustomMonsterProvider customMonsterProvider = new CustomMonsterProvider();
        assertEquals(customMonsterProvider.getAssociatedLevelPath(),
            Path.of("./nonExistendDir/nonExistentFile.xml"));
    }

    @Test
    void getDirectionPathMap() {
        CustomMonsterProvider customMonsterProvider = new CustomMonsterProvider();
        assertNull(customMonsterProvider.getDirectionPathMap());
    }

    @Test
    void getDirectionList() {
        assertEquals(new CustomMonsterProvider().getDirectionList(), List.of());
    }
}