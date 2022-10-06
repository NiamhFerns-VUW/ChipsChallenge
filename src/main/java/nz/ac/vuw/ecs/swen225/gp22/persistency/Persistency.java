package nz.ac.vuw.ecs.swen225.gp22.persistency;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chip;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Wall;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Persistency {

    /**
     * loads xml file at path into a gameSave object.
     * @param path
     * @return
     */
    public static GameSave loadGameSave(Path path) {
        Document document = FileHandler.getXML(path);
        GameSave gameSave = new GameSave();
        gameSave.fromXml(document.getRootElement());
        return gameSave;
    }

    /**
     * writes the xml of a gameSave object to path given.
     * @param gameSave
     * @param path
     */
    public static void saveGameSave(GameSave gameSave, Path path) {
        FileHandler.saveXML(gameSave.toXml(),path.toString());
    }


    public static void main(String[] args) throws JsonProcessingException, DocumentException {
        Cell cell = new Cell();
        Key key = new Key("Green");
        Chip chip = new Chip(Direction.Down,new Coord(-1,-1));
        List<Entity> entities = new ArrayList<>();
        entities.add(chip);
        entities.add(key);
        FreeTile ft = new FreeTile();
        cell.setStoredTile(ft);
        cell.setEntities((ArrayList<Entity>) entities);
        XmlMapper mapper = new XmlMapper();
        String cellXmlString = mapper.writeValueAsString(cell);
        System.out.println(Converter.elementString(DocumentHelper.parseText(cellXmlString).getRootElement()));
    }
}
