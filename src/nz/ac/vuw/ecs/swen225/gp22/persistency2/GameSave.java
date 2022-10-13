/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;

/**
 * Represents a state in the game which can be serialized and deserialized.
 */
public class GameSave {

    public final int CELLS_WIDTH = 21;
    public final int CELLS_HEIGHT = 21;

    private int time;
    private List<Entity> inventory;

    private List<Cell> cellList;

    @JsonCreator
    public GameSave(
        @JsonProperty("cellList") List<Cell> cellList,
        @JsonProperty("time") int time,
        @JsonProperty("inventory") List<Entity> inventory) {
        this.cellList = cellList;
        this.time = time;
        this.inventory = inventory;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }

    /**
     * @return indented xml representing the current object.
     */
    @Override
    public String toString() {
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Entity> getInventory() {
        return inventory;
    }

    public void setInventory(List<Entity> inventory) {
        this.inventory = inventory;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        return time+inventory.hashCode()+cellList.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GameSave && obj.hashCode() == this.hashCode();
    }
}
