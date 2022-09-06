package gp22.persistency.domain_classes;

import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.persistency.domain_classes.Entity;
import nz.ac.vuw.ecs.swen225.gp22.persistency.domain_classes.FreeTile;

public class Cell {
    FreeTile freeTile;
    List<Entity> entities;

    public Cell(FreeTile freeTile, List<Entity> entities) {
        this.freeTile = freeTile;
        this.entities = entities;
    }
    Cell(){

    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setFreeTile(FreeTile freeTile) {
        this.freeTile = freeTile;
    }

    public FreeTile getFreeTile() {
        return freeTile;
    }
}
