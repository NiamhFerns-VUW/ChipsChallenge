/**
 * @author Micky Snadden
 */
package nz.ac.vuw.ecs.swen225.gp22.persistency2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave.GameSaveDeserializer;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave.GameSaveSerializer;

/**
 * Represents a state in the game which can be serialized and deserialized.
 */
@JsonDeserialize(using = GameSaveDeserializer.class)
@JsonSerialize(using = GameSaveSerializer.class)
public class GameSave {

    final int CELLS_WIDTH = 21;
    final int CELLS_HEIGHT = 21;

    private Cell[][] cells;
    private int time;
    private List<Entity> inventory;

    public GameSave(Cell[][] cells, int time, List<Entity> inventory) {
        this.cells = cells;
        this.time = time;
        this.inventory = inventory;
    }

    /**
     * Used by jackson lib to Serialize a GameSave object to xml.
     */
    public static class GameSaveSerializer extends StdSerializer<GameSave> {

        private final XmlMapper mapper = new XmlMapper();

        protected GameSaveSerializer(Class<GameSave> t) {
            super(t);
        }

        /**
         * GameSaveSerializer constructor.
         */
        public GameSaveSerializer() {
            this(null);
        }


        /**
         * @param gameSave
         * @param jsonGenerator
         * @param serializerProvider
         * @throws IOException
         */
        @Override
        public void serialize(GameSave gameSave, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
        }
    }

    /**
     * Used by jackson lib to Deserialize a serialized GameSave object.
     */
    public static class GameSaveDeserializer extends StdDeserializer<GameSave> {

        /**
         *
         */
        public GameSaveDeserializer() {
            this(null);
        }

        /**
         * @param vc
         */
        public GameSaveDeserializer(Class<?> vc) {
            super(vc);
        }

        /**
         * @param jp
         * @param ctxt
         * @return
         * @throws IOException
         */
        @Override
        public GameSave deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
            return null;
        }
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

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
