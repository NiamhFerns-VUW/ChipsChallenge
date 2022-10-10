package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile;
import org.junit.jupiter.api.Test;

class LevelMapsTest {


    @Test
    void getMap1() {
        Map<Coord, Cell> map1 = LevelMaps.getMap1();
        Cell cell = map1.get(new Coord(0,0));
        assertSame(cell.getStoredTile().getClass(), FreeTile.class);

    }

    @Test
    void getMap2() {
        Map<Coord, Cell> map2 = LevelMaps.getMap2();
        Cell cell = map2.get(new Coord(0,0));
        assertSame(cell.getStoredTile().getClass(), FreeTile.class);
    }

    @Test
    void getMap1In2d() {
        Cell[][] map1In2d = LevelMaps.getMap1In2d();
        assertSame(map1In2d[0][0].getStoredTile().getClass(), FreeTile.class);
    }

    @Test
    void getMap2In2d() {
        Cell[][] map2In2d = LevelMaps.getMap2In2d();
        assertSame(map2In2d[0][0].getStoredTile().getClass(), FreeTile.class);
    }

    public static Map<Coord, Cell> getTestMap() {
        HashMap<Coord,Cell> testMap = new HashMap<>();
        testMap.put(new Coord(0,0),new Cell(
            new FreeTile(),
            new ArrayList<>(List.of())
        ));
        testMap.put(new Coord(0,1),new Cell(
            new FreeTile(),
            new ArrayList<>(List.of())
        ));
        testMap.put(new Coord(1,0),new Cell(
            new FreeTile(),
            new ArrayList<>(List.of())
        ));
        testMap.put(new Coord(1,1),new Cell(
            new FreeTile(),
            new ArrayList<>(List.of())
        ));
        return testMap;
    }
}