/**
 * @author Micky Snadden 300569572
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.CharMatcher;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Stream;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.CustomMovingEntityService;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.DefaultCustomMovingEntityServiceProvider;

/**
 * Helper class for dealing with GameSave objects.
 */
public class GameSaveHelper {

    /**
     * Path that GameSaveHelper will use to load GameSave objects that have an empty inventory,
     * time set to 100 and cells list representing a start of game state.
     */
    public static final Path LEVEL_PATH = Path.of("./levels/");
    public static final Path SAVE_PATH = Path.of("./saves/");

    /**
     * Convenience method so modules could get Level 1 to play around with.
     * @return
     */
    public static GameSave getLevel1GameSave() {
        GameSave gameSave = new GameSave(LevelMap.get().getLevel1CellList(), 100, List.of());
        return gameSave;
    }
    /**
     * Convenience method so modules could get Level 2 to play around with.
     * @return
     */
    public static GameSave getLevel2GameSave() throws IOException {
        GameSave gameSave = loadGameSave(Path.of(LEVEL_PATH+"/level2.xml"));
        ServiceLoader<CustomMovingEntityService> loader = ServiceLoader.load(CustomMovingEntityService.class);
        if (loader.findFirst().isEmpty()) {
            return gameSave;
        }
        replaceDefaultCustomEntities(gameSave,loader);
        return gameSave;
    }

    /**
     * Used to replace default custom entities with first implementation found in jar
     * (For convenience getLevelXGameSave methods).
     * @param gameSave gamesave object to operate on
     * @param loader the service loader holding the providers
     */
    private static void replaceDefaultCustomEntities(GameSave gameSave,ServiceLoader<CustomMovingEntityService> loader) {
        List<Cell> cellList = gameSave.getCellList();
        List<Cell> cellsWithCustomEntities = cellList.stream()
            .filter(cell -> cell.getEntities().stream()
                .anyMatch(e -> e instanceof CustomMovingEntityService))
            .toList();
        cellsWithCustomEntities.forEach(cell -> {
            List<Entity> origEntities = cell.getEntities();
            List<Entity> customEntities = cell.getEntities().stream()
                .filter(e -> e instanceof CustomMovingEntityService).toList();
            customEntities.forEach(customEntity -> {
                int indexOfCustomEntity = origEntities.indexOf(customEntity);
                if (loader.findFirst().isPresent()) {
                    origEntities.set(indexOfCustomEntity,loader.findFirst().get());
                }
            });
            cell.setEntities(origEntities);
        });
    }

    /**
     * Used to load a game save but look for a service provider and replace the default implementation
     * with the service providers implementation.
     * @param path
     * @return
     * @throws IOException
     */
    public static GameSave loadLevel(Path path) throws IOException {
        GameSave gameSave = loadGameSave(path);
        String theDigits = CharMatcher.inRange('0', '9').retainFrom(path.toString()); // 123
        int levelNumber = Integer.parseInt(theDigits);
        ServiceLoader<CustomMovingEntityService> loader = ServiceLoader.load(CustomMovingEntityService.class);
        if (loader.findFirst().isEmpty()) {
            return loadGameSave(path);
        }
        else {
            // get valid provider
            Optional<Provider<CustomMovingEntityService>> validProvider = loader.stream()
                .filter(customMovingEntityServiceProvider -> {
                    String digitsInCustomEntityAssocFile = CharMatcher.inRange('0', '9').retainFrom(
                        customMovingEntityServiceProvider.get().getAssociatedLevelFile()
                            .toString());
                    int assocNumber = Integer.parseInt(digitsInCustomEntityAssocFile);
                    return assocNumber == levelNumber; // associated level number is same as the filenames level number, good to go.
                }).findFirst();
            if (validProvider.isPresent()) {
                Provider<CustomMovingEntityService> customMovingEntityServiceProvider = validProvider.get();
                replaceDefaultsWithProvider(gameSave,customMovingEntityServiceProvider);
                return gameSave;
            }
        }
        return gameSave;
    }

    /**
     * Replaces instances in gameSave of CustomMovingEntityService with specific provider implementation.
     * @param gameSave
     * @param provider
     */
    private static void replaceDefaultsWithProvider(GameSave gameSave,Provider<CustomMovingEntityService> provider) {
        List<Cell> cellList = gameSave.getCellList();
        List<Cell> cellsWithCustomEntities = cellList.stream()
            .filter(cell -> cell.getEntities().stream()
                .anyMatch(e -> e instanceof CustomMovingEntityService))
            .toList();
        cellsWithCustomEntities.forEach(cell -> {
            List<Entity> origEntities = cell.getEntities();
            List<Entity> customEntities = cell.getEntities().stream()
                .filter(e -> e instanceof CustomMovingEntityService).toList();
            customEntities.forEach(customEntity -> {
                int indexOfCustomEntity = origEntities.indexOf(customEntity);
                origEntities.set(indexOfCustomEntity,provider.get());
            });
            cell.setEntities(origEntities);
        });
    }

    /**
     *
     * @param path path of gamesave file
     * @return loaded gamesave object
     * @throws IOException if jackson lib encounters errors trying to locate file or parsing xml.
     */
    public static GameSave loadGameSave(Path path) throws IOException {
        XmlMapper mapper = new XmlMapper();
        GameSave gameSave = mapper.readValue(path.toFile(), GameSave.class);
        return gameSave;
    }

    /**
     * Uses jacksons XmlMapper to write the gamesave to designated save directory.
     * @param gameSave
     * @throws IOException
     */
    public static void saveGameSave(GameSave gameSave) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(SAVE_PATH + "/save" + gameSave.hashCode()+".xml");
        mapper.writeValue(file,gameSave);
    }

}
