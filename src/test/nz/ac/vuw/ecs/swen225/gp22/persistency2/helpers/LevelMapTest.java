/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;
import nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LevelMapTest {

    // TODO add test that randomly selects cell within bounds and checks gameSaves cell at coord is same as it.

    @Test
    @DisplayName("check initLevel1Map results in non empty map field in LevelMap for level 1")
    void initLevel1Map() {
        LevelMap levelMap = LevelMap.get();
        assertFalse(levelMap.getLevel1Map().isEmpty());
    }

    @Test
    @DisplayName("check initLevel2Map results in non empty map field in LevelMap for level 2")
    void initLevel2Map() {
        LevelMap levelMap = LevelMap.get();
        assertFalse(levelMap.getLevel2Map().isEmpty());
    }

    @Test
    void getLevel1Map() {
        LevelMap levelMap = LevelMap.get();
        Map<Coord, Cell> level1Map = levelMap.getLevel1Map();
        assertEquals(level1Map,levelMap.getLevel1Map());
    }

    @Test
    void getLevel2Map() {
        LevelMap levelMap = LevelMap.get();
        Map<Coord, Cell> level2Map = levelMap.getLevel2Map();
        assertEquals(level2Map,levelMap.getLevel2Map());
    }

    @Test
    @DisplayName("check LevelMap singleton get() returns same object")
    void get() {
        LevelMap levelMap = LevelMap.get();
        LevelMap levelMap2 = LevelMap.get();
        assertEquals(levelMap,levelMap2);
    }
    private String twoDimensionalCellsToString(Cell[][] cells) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                System.out.print(" "+cell);
                stringBuilder.append(cell).append(" ");
            }
            System.out.println();
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    public Map<Coord,Cell> getTestMap() {
        HashMap<Coord, Cell> coordCellHashMap = new HashMap<>();
        coordCellHashMap.put(new Coord(0,0),new Cell(
            new FreeTile(),
            List.of()
        ));
        coordCellHashMap.put(new Coord(0,1),new Cell(
            new FreeTile(),
            List.of()
        ));
        coordCellHashMap.put(new Coord(1,0),new Cell(
            new FreeTile(),
            List.of()
        ));
        coordCellHashMap.put(new Coord(1,1),new Cell(
            new FreeTile(),
            List.of()
        ));
        return coordCellHashMap;
    }
}