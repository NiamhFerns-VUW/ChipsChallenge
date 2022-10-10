package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class LevelPathTest {

    @Test
    void getLevelPath() {
        boolean equals = LevelPath.LEVEL1.getLevelPath().equals(Path.of("./levels/level1.xml"));
        assertTrue(equals);
    }
}