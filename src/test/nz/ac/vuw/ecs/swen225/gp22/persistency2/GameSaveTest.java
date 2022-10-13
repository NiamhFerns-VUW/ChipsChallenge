package nz.ac.vuw.ecs.swen225.gp22.persistency2;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chip;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.GameSaveHelper;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.LevelHelper;
import org.junit.jupiter.api.Test;

class GameSaveTest {

    private final XmlMapper mapper;
    public GameSaveTest() {
        mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @Test
    public void validateLevel1And2GameSaves() throws IOException {
        GameSave level1GameSave = GameSaveHelper.getLevel1GameSave();
        assertTrue(validateGameSave(level1GameSave));
        GameSave level2GameSave = GameSaveHelper.getLevel2GameSave();
        assertTrue(validateGameSave(level2GameSave));
    }

    public boolean validateGameSave(GameSave gameSave) throws JsonProcessingException {
        String gameSaveXml = mapper.writeValueAsString(gameSave);
        GameSave gameSaveFromXml = mapper.readValue(gameSaveXml, GameSave.class);
        boolean gameSavesEqual = gameSaveFromXml.equals(gameSave);
        String deserialisedReserialised = mapper.writeValueAsString(gameSaveFromXml);
        boolean equalXml = deserialisedReserialised.equals(gameSaveXml);
        return gameSavesEqual && equalXml;
    }
    public GameSave getTestGameSave() throws JsonProcessingException {
        List<Cell> cells1 = new ArrayList<>();
        cells1.add(new Cell(
            new FreeTile(),
            List.of(new Chip(Direction.None, new Coord(-1, -1))),
            new Coord(0,0)
        ));
        cells1.add(new Cell(
            new FreeTile(),
            List.of(), new Coord(0,1)
        ));
        cells1.add(new Cell(
            new FreeTile(),
            List.of(), new Coord(1,0)
        ));
        cells1.add(new Cell(
            new FreeTile(),
            List.of(),new Coord(1,1)
        ));
        ArrayList<Cell> cells = new ArrayList<>(cells1);
        GameSave gameSave = new GameSave(cells, 100, List.of());
        return gameSave;
    }
    @Test
    public void sanityCheckTest() throws JsonProcessingException {
        GameSave testGameSave = getTestGameSave();
        System.out.println(validateGameSave(testGameSave));
    }
}