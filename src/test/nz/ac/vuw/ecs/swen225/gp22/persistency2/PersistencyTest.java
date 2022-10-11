package nz.ac.vuw.ecs.swen225.gp22.persistency2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import static nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.LevelPath.*;

import nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.LevelPath;
import org.junit.jupiter.api.Test;

class PersistencyTest {

    @Test
    void loadGameSave() {
        GameSave gameSave = Persistency.loadGameSave(LEVEL1.getLevelPath());
        assertThrows(RuntimeException.class,()->{
            GameSave gameSave1 = Persistency.loadGameSave(Path.of(""));
        });
    }

    @Test
    void loadLevel() {
        GameSave gameSave = Persistency.loadLevel(LEVEL1.getLevelPath());
        GameSave gameSave2 = Persistency.loadLevel(LEVEL2.getLevelPath());
    }

    @Test
    void saveGameSave() {
        GameSave gameSave = Persistency.loadLevel(LEVEL2.getLevelPath());
        Persistency.saveGameSave(gameSave, Path.of("./saves/saveTest.xml"));
        assertThrows(RuntimeException.class,()->{
            Persistency.saveGameSave(gameSave,Path.of(""));
        });
    }
}