package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell.CellDeserializer;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell.CellSerializer;

/**
 * Each Cell keeps track of the tile it is made up of and the entities on it.
 */
@JsonDeserialize(using = CellDeserializer.class)
@JsonSerialize(using = CellSerializer.class)
public class Cell {
	private FreeTile storedTile;
	private List<Entity> entities;
	private Coord coord;
	/**
	 * Default Constructor for the cell leaves the tile as a blank FreeTile
	 */
	public Cell() {
		this.storedTile = new FreeTile();
		this.entities = new ArrayList<Entity>();
		this.coord = new Coord(-1,-1);
	}
	public Cell(FreeTile storedTile) {
		this.storedTile = storedTile;
		entities = new ArrayList<Entity>();
	}
	public Cell(FreeTile storedTile, ArrayList<Entity> entities) {
		this.storedTile = storedTile;
		this.entities = entities;
	}
	public Cell(FreeTile storedTile, ArrayList<Entity> entities,Coord coord) {
		this.storedTile = storedTile;
		this.entities = entities;
		this.coord = coord;
	}
	public boolean beforeMoveInto(MovingEntity e, Direction d) {
		return storedTile.onMoveInto(e, d, this) && entities.stream().allMatch(a -> a.interactBefore(e, d, this));
	}

	public boolean afterMoveInto(MovingEntity e, Direction d) {
		return entities.stream().allMatch(a -> a.interactAfter(e, d, this));
	}
	
	public FreeTile getStoredTile() {
		return storedTile;
	}
	public void setStoredTile(FreeTile newTile) {
		storedTile = newTile;
	}
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entList) {
		entities = entList;
	}

	public void removeEntity(Entity e) {
		entities = new ArrayList<Entity>(entities.stream().filter(entity -> entity != e).toList());
	}

	public static class CellSerializer extends StdSerializer<Cell> {

		protected CellSerializer(Class<Cell> t) {
			super(t);
		}
		public CellSerializer() {
			this(null);
		}

		@Override
		public void serialize(Cell cell, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeStartObject();
			serializerProvider.defaultSerializeField("storedTile",cell.getStoredTile(),jsonGenerator);
			serializerProvider.defaultSerializeField("coord",cell.getCoord(),jsonGenerator);

			jsonGenerator.writeObjectFieldStart("entities");
			List<Entity> entities1 = cell.getEntities();
			entities1.forEach(e->{
				try {
					jsonGenerator.writeObjectField("entities",e);
//					System.out.println("hey!");
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			});
			jsonGenerator.writeEndObject();
			jsonGenerator.writeEndObject();
		}
	}
	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public static class CellDeserializer extends StdDeserializer<Cell> {

		protected CellDeserializer(Class<?> vc) {
			super(vc);
		}
		public CellDeserializer() {
			this(null);
		}
		private final XmlMapper mapper = new XmlMapper();

		@Override
		public Cell deserialize(JsonParser jsonParser,
			DeserializationContext deserializationContext) throws IOException, JacksonException {
			Cell cell = new Cell();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
			TreeNode storedTileNode = treeNode.get("storedTile");
			TreeNode entitiesNode = treeNode.get("entities");
			TreeNode coordNode = treeNode.get("coord");

			FreeTile freeTile = mapper.treeToValue(storedTileNode, FreeTile.class);
			Coord coord1 = mapper.treeToValue(coordNode, Coord.class);

			cell.setCoord(coord1);
			cell.setStoredTile(freeTile);

			String entitiesXml = mapper.writeValueAsString(entitiesNode);
			List<Entity> entities = mapper.readValue(entitiesXml.toString(),
				new TypeReference<List<Entity>>() {
				});

			cell.setEntities(entities);
			return cell;
		}
	}
}

