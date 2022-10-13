package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave;
import org.junit.jupiter.api.Test;

class GameSaveHelperTest {

    @Test
    void getLevel1GameSave() {
        // compare against source of truth source code levels
    }

    @Test
    void getLevel2GameSave() {
        // compare against source of truth source code levels
    }

    @Test
    void loadGameSave() throws IOException {
        // TODO find way to do with mockito
        // create gameSave
        GameSave level1GameSave = GameSaveHelper.getLevel1GameSave();
        // save it
        GameSaveHelper.saveGameSave(level1GameSave);
        // load it back, ensure equal to orig
        GameSave gameSave = GameSaveHelper.loadGameSave(
            Path.of(
                GameSaveHelper.SAVE_PATH.toString() + "/save" + level1GameSave.hashCode() + ".xml")
        );
        // check deserialized gamesave is equal to source code loaded ("source of truth") game save.
        assertEquals(level1GameSave,gameSave);
    }

    @Test
    void saveGameSave() throws IOException {
        // TODO find way to do with mockito
        GameSave level2GameSave = GameSaveHelper.getLevel2GameSave();
        GameSaveHelper.saveGameSave(level2GameSave);

        GameSave gameSave = GameSaveHelper.loadGameSave(
        Path.of(
            GameSaveHelper.SAVE_PATH.toString() + "/save" + level2GameSave.hashCode() + ".xml")
        );
        assertEquals(level2GameSave,gameSave);

    }
}