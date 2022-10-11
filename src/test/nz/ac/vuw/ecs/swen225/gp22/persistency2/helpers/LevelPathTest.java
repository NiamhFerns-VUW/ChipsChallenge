package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import static nz.ac.vuw.ecs.swen225.gp22.persistency.Level.*;
import org.junit.jupiter.api.Test;

class LevelPathTest {

    @Test
    void getLevelPath() {
        boolean equals = LevelPath.LEVEL1.getLevelPath().equals(LEVEL1.getLevelPath());
        assertTrue(equals);
    }
}