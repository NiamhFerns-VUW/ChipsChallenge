package nz.ac.vuw.ecs.swen225.gp22.persistency2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chip;
import nz.ac.vuw.ecs.swen225.gp22.domain.Coord;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.Treasure;
import nz.ac.vuw.ecs.swen225.gp22.persistency.DefaultCustomMonsterProvider;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave.GameSaveDeserializer;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave.GameSaveSerializer;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.LevelMaps;

@JsonDeserialize(using = GameSaveDeserializer.class)
@JsonSerialize(using = GameSaveSerializer.class)
public class GameSave {

    private Cell[][] cells = new Cell[21][21];
    private int time;
    private List<Entity> inventory;

    @JsonIgnore
    private Map<Coord,Cell> cellMap = new HashMap<>();

    public Map<Coord, Cell> getCellMap() {
        return cellMap;
    }

    public void setCellMap(
        Map<Coord, Cell> cellMap) {
        this.cellMap = cellMap;
        cellMap.keySet().forEach(coord->{
            Cell cell = cellMap.get(coord);
            cells[coord.y()][coord.x()] = cell;
        });
    }

    public GameSave(Cell[][] cells, int time, List<Entity> inventory) {
        this.cells = cells;
        this.time = time;
        this.inventory = inventory;
        this.cellMap = new HashMap<>();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                cellMap.put(new Coord(row,col),cell);
            }
        }
    }
    public GameSave(Map<Coord,Cell> cellMap, int time, List<Entity> inventory) {
        this.cellMap = cellMap;
        this.time = time;
        this.inventory = inventory;
    }

    public GameSave() {
    }

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

    public static class GameSaveSerializer extends StdSerializer<GameSave> {
        private final XmlMapper mapper = new XmlMapper();
        protected GameSaveSerializer(Class<GameSave> t) {
            super(t);
        }
        public GameSaveSerializer() {
            this(null);
        }


        @Override
        public void serialize(GameSave gameSave, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("time",gameSave.getTime());

            List<Entity> inventory1 = gameSave.getInventory();
            jsonGenerator.writeObjectFieldStart("inventory");
            inventory1.forEach(e->{
                try {
                    jsonGenerator.writeObjectField("entities",e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            jsonGenerator.writeEndObject();

            Map<Coord, Cell> cellMap1 = gameSave.getCellMap();
            jsonGenerator.writeObjectFieldStart("cells");
            cellMap1.keySet()
                    .forEach(key->{
                        Cell cell = cellMap1.get(key);
                        try {
                            String string = mapper.writeValueAsString(cell);
                            jsonGenerator.writeObjectField("cell",cell);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });


            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        }
    }

    public static class GameSaveDeserializer extends StdDeserializer<GameSave> {

        public GameSaveDeserializer() {
            this(null);
        }

        public GameSaveDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public GameSave deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
            GameSave gameSave = new GameSave();
            XmlMapper mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // time
            JsonNode jsonNode = jp.getCodec().readTree(jp);
            int gameSaveTime = jsonNode.get("time").asInt();
            gameSave.setTime(gameSaveTime);
            // inventory
            TreeNode inventoryNode = jsonNode.get("inventory");
            String xmlTing = mapper.writeValueAsString(inventoryNode);
            List<Entity> entities = mapper.readValue(xmlTing.toString(),
                new TypeReference<List<Entity>>() {
                });
            gameSave.setInventory(entities);
            // cells
            JsonNode cellsNode = jsonNode.get("cells").get("cell");
            Iterator<JsonNode> cellNodeIterator = cellsNode.iterator();
            HashMap<Coord, Cell> coordCellHashMap = new HashMap<>();
            cellNodeIterator.forEachRemaining(cellNode->{
                try {
                    Cell cell = new ObjectMapper().readValue(cellNode.toString(), Cell.class);
                    coordCellHashMap.put(new Coord(cell.getCoord().x(),cell.getCoord().y()),cell);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
            gameSave.setCellMap(coordCellHashMap);

            return gameSave;
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                cellMap.put(new Coord(row,col),cell);
            }
        }

    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Entity> getInventory() {
        return inventory;
    }

    public void setInventory(List<Entity> inventory) {
        this.inventory = inventory;
    }
}
