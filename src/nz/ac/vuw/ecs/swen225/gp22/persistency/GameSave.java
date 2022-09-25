package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;

public class GameSave {

    public Cell[][] cells;
    public int time;
    public List<Entity> inventory;
    public GameSave(){}

}
