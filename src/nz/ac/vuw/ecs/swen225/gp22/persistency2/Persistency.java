package nz.ac.vuw.ecs.swen225.gp22.persistency2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chip;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.persistency.CustomMonster;
import nz.ac.vuw.ecs.swen225.gp22.persistency.CustomMonsterProvider;
import nz.ac.vuw.ecs.swen225.gp22.persistency.FileHandler;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.LevelMaps;
import org.dom4j.Document;

public class Persistency {

    /**
     * loads xml file at path into a gameSave object.
     * @param path
     * @return
     */
    public static GameSave loadGameSave(Path path) {
        XmlMapper mapper = new XmlMapper();
        try {
            GameSave gameSave = mapper.readValue(Paths.get(path.toString()).toFile(),
                GameSave.class);
            return gameSave;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * writes the xml of a gameSave object to path given.
     * @param gameSave
     * @param path
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
