package nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers;

import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave;

public class GameSaveHelper {

    public static GameSave getLevel1GameSave() {
        Map<Coord, Cell> level1Map = LevelMap.get().getLevel1Map();
        GameSave gameSave = new GameSave(LevelMap.get().getLevel1CellList(), 100, List.of());
        return gameSave;
    }
    public static GameSave getLevel2GameSave() {
        Map<Coord, Cell> level1Map = LevelMap.get().getLevel2Map();
        GameSave gameSave = new GameSave(LevelMap.get().getLevel2CellList(), 100, List.of());
        return gameSave;
    }

}
