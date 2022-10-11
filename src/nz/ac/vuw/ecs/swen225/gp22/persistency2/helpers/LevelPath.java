/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import java.nio.file.Path;

public enum LevelPath {

    // enum constants calling the enum constructors
    LEVEL1(Path.of("./levels/level1.xml")),
    LEVEL2(Path.of("./levels/level2.xml"));

    private final Path levelPath;

    // private enum constructor
    LevelPath(Path levelPath) {
        this.levelPath = levelPath;
    }

    public Path getLevelPath() {
        return levelPath;
    }
}
