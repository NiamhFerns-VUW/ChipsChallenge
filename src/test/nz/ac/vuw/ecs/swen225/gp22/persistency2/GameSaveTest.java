package nz.ac.vuw.ecs.swen225.gp22.persistency2;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;
import static nz.ac.vuw.ecs.swen225.gp22.persistency.Level.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class GameSaveTest {

    private final GameSave mock = mock(GameSave.class);

    @Test
    void getCellMap() {
        GameSave testGameSave = createTestGameSave();
        HashMap<Coord, Cell> coordCellHashMap = new HashMap<>();
        coordCellHashMap.put(new Coord(0,0),new Cell());
        testGameSave.setCellMap(coordCellHashMap);
        assertEquals(testGameSave.getCellMap(),coordCellHashMap);
    }

    @Test
    void setCellMap() {
        HashMap<Coord, Cell> coordCellHashMap = new HashMap<>();
        coordCellHashMap.put(new Coord(0,0),new Cell(new FreeTile(),List.of()));
        coordCellHashMap.put(new Coord(0,1),new Cell(new FreeTile(),List.of()));
        GameSave testGameSave = createTestGameSave();
        testGameSave.setCellMap(coordCellHashMap);
        assertEquals(coordCellHashMap,testGameSave.getCellMap());
    }

    @Test
    void testToString() {
        GameSave testGameSave = createTestGameSave();
        String str = "<GameSave>\n"
            + "  <time>100</time>\n"
            + "  <inventory/>\n"
            + "  <cells>\n"
            + "    <cell>\n"
            + "      <storedTile _class=\"nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile\"/>\n"
            + "      <coord/>\n"
            + "      <entities/>\n"
            + "    </cell>\n"
            + "    <cell>\n"
            + "      <storedTile _class=\"nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile\"/>\n"
            + "      <coord/>\n"
            + "      <entities/>\n"
            + "    </cell>\n"
            + "    <cell>\n"
            + "      <storedTile _class=\"nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile\"/>\n"
            + "      <coord/>\n"
            + "      <entities/>\n"
            + "    </cell>\n"
            + "    <cell>\n"
            + "      <storedTile _class=\"nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile\"/>\n"
            + "      <coord/>\n"
            + "      <entities/>\n"
            + "    </cell>\n"
            + "  </cells>\n"
            + "</GameSave>\n";
        assertEquals(testGameSave.toString(),str);
    }

    @Test
    void getCells() {
        GameSave testGameSave = createTestGameSave();
        assertEquals(testGameSave.getCells(),testGameSave.getCells());
    }

    @Test
    void setCells() {
        GameSave testGameSave = createTestGameSave();
        Cell[][] cells = new Cell[4][4];
        testGameSave.setCells(cells);
        assertEquals(testGameSave.getCells(),cells);
    }

    @Test
    void getTime() {
        GameSave gameSave = Persistency.loadGameSave(LEVEL1.getLevelPath());
        assertEquals(100, gameSave.getTime());
    }

    @Test
    void setTime() {
        GameSave gameSave = Persistency.loadGameSave(LEVEL1.getLevelPath());
        gameSave.setTime(99);
        assertEquals(99,gameSave.getTime());
    }

    @Test
    void getInventory() {
        GameSave gameSave = Persistency.loadGameSave(LEVEL1.getLevelPath());
        assertEquals(gameSave.getInventory(),List.of());
    }

    @Test
    @DisplayName("check set inventory works correctly")
    void setInventory() {
        GameSave gameSave = Persistency.loadGameSave(LEVEL1.getLevelPath());
        assertEquals(gameSave.getInventory(),List.of());
        List<Entity> keys = List.of(new Key("Green"), new Key("Yellow"));
        gameSave.setInventory(keys);
        assertEquals(gameSave.getInventory(),keys);
    }
    public static GameSave createTestGameSave() {
        HashMap<Coord, Cell> coordCellHashMap = new HashMap<>();
        coordCellHashMap.put(new Coord(0,0),new Cell(new FreeTile(),List.of()));
        coordCellHashMap.put(new Coord(0,1),new Cell(new FreeTile(),List.of()));
        coordCellHashMap.put(new Coord(1,0),new Cell(new FreeTile(),List.of()));
        coordCellHashMap.put(new Coord(1,1),new Cell(new FreeTile(),List.of()));
        GameSave gameSave = new GameSave(coordCellHashMap,100,List.of());
        return gameSave;
    }

}