/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.MovingEntity;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin.CustomMonster;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin.CustomMonsterProvider;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin.DefaultCustomMonsterProvider;

public class Persistency {

    /**
     * loads xml file at path into a gameSave object.
     * @param path path of serialized gameSave
     * @return deserialized gameSave from path
     */
    public static GameSave loadGameSave(Path path) {
        XmlMapper mapper = new XmlMapper();
        try {
            return mapper.readValue(Paths.get(path.toString()).toFile(),
                GameSave.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Like loadGameSave except we look for custom MonsterProviders and replace them with either default impl
     * or impl in jar.
     * @param levelPath path to serialized game save (level version: empty inventory & time 100 )
     * @return deserialized gameSave with customMonsters replaced by instances of service provider implementations or default if none present.
     */
    public static GameSave loadLevel(Path levelPath) {
        GameSave gameSave = loadGameSave(levelPath);
        ServiceLoader<CustomMonster> loader = ServiceLoader.load(CustomMonster.class);

        // Looking for service providers
        Optional<Provider<CustomMonster>> any = loader.stream().filter(e -> {
            CustomMonster customMonster = e.get();
            CustomMonsterProvider cm = (CustomMonsterProvider) customMonster;
            // check that customMonster has same level path
            Path associatedLevelPath = cm.getAssociatedLevelPath();
            return associatedLevelPath.toString().equals(levelPath.toString());
        }).findAny();
        // TODO refactor as not very dry.
        // found service provider
        if (any.isPresent()) {
            Provider<CustomMonster> customMonsterProvider = any.get();
            // finding CustomMonster in entities and replacing with implementation from service loader
            Map<Coord, Cell> cellMap = gameSave.getCellMap();
            cellMap.keySet()
                    .forEach(coord->{
                        Cell cell = cellMap.get(coord);
                        List<Entity> origEntities = cell.getEntities();
                        List<Entity> customMonsterProviders = origEntities.stream()
                            .filter(e -> e instanceof MovingEntity && e.getClass().getSimpleName().equals("CustomMonsterProvider")).toList();
                        customMonsterProviders.forEach(e->{
                            origEntities.set(origEntities.indexOf(e),customMonsterProvider.get());
                        });
                        cell.setEntities(origEntities);
                    });
            gameSave.setCellMap(cellMap);
        }
        // didn't find service provider
        else {
            Map<Coord, Cell> cellMap = gameSave.getCellMap();
            cellMap.keySet()
                .forEach(coord->{
                    Cell cell = cellMap.get(coord);
                    List<Entity> origEntities = cell.getEntities();
                    List<Entity> customMonsterProviders = origEntities.stream()
                        .filter(e -> e instanceof MovingEntity && e.getClass().getSimpleName().equals("CustomMonsterProvider")).toList();
                    customMonsterProviders.forEach(e->{
                        origEntities.set(origEntities.indexOf(e), new DefaultCustomMonsterProvider());
                    });
                    cell.setEntities(origEntities);
                });
            gameSave.setCellMap(cellMap);
        }
        return gameSave;
    }

    /**
     * writes the xml of a gameSave object to path given.
     * @param gameSave gameSave object to serialize and write to file
     * @param path destination of serialized gamesave
     */
    public static void saveGameSave(GameSave gameSave, Path path) {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(Paths.get(path.toUri()).toFile(),gameSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
