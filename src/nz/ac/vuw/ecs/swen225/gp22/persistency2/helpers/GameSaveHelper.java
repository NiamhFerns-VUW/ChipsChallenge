package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.CustomMovingEntityService;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.DefaultCustomMovingEntityServiceProvider;

public class GameSaveHelper {

    public static final Path LEVEL_PATH = Path.of("./levels/");
    public static final Path SAVE_PATH = Path.of("./saves/");
    public static GameSave getLevel1GameSave() {
        GameSave gameSave = new GameSave(LevelMap.get().getLevel1CellList(), 100, List.of());
        return gameSave;
    }
    public static GameSave getLevel2GameSave() throws IOException {
        GameSave gameSave = loadGameSave(Path.of(LEVEL_PATH+"/level2.xml"));
        ServiceLoader<CustomMovingEntityService> loader = ServiceLoader.load(CustomMovingEntityService.class);
        if (loader.findFirst().isEmpty()) {
            return gameSave;
        }
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
        return gameSave;
    }
    public static GameSave loadGameSave(Path path) throws IOException {
        XmlMapper mapper = new XmlMapper();
        GameSave gameSave = mapper.readValue(path.toFile(), GameSave.class);
        return gameSave;
    }
    public static void saveGameSave(GameSave gameSave) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(SAVE_PATH + "/save" + gameSave.hashCode()+".xml");
        mapper.writeValue(file,gameSave);
    }

}
