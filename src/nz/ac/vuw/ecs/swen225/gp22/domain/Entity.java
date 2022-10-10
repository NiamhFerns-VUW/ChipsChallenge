package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.monsterplugin.CustomMonsterProvider;

/**
 * The Entity interface is for the various things that the player could interact with.
 * Different from tiles, these can stack on top of each other (for example, a monster standing on the key, or a box pushed onto treasure).
 * 
 */
@JsonTypeInfo(use = Id.CLASS)
@JsonSubTypes({
	@Type(Chip.class),
	@Type(Key.class),
	@Type(CustomMonsterProvider.class),
	@Type(MoveableBlock.class),
	@Type(Treasure.class)
})
public interface Entity {
	/**
	 * The interactBefore method causes the entity to perform an action based on its class, before the player moves
	 * onto this entity's tile.
	 * 
	 * @param e - The Entity interacting with this Entity.
	 * @param d - The direction the interacting Entity is moving.
	 * @param c - The current coordinates of this entity.
	 * 
	 * @return Returns true if the interaction is successful, false if there is a failure
	 * (such as the interaction causing a box to attempt to move into a wall).
	 */
	boolean interactBefore(MovingEntity e, Direction d, Cell myCell);
	/**
	 * The interactAfter method causes the entity to perform an action based on its class, after the player moves
	 * onto this entity's tile.
	 *
	 * @param e - The Entity interacting with this Entity.
	 * @param d - The direction the interacting Entity is moving.
	 * @param c - The current coordinates of this entity.
	 *
	 * @return Returns true if the interaction is successful, false if there is a failure
	 * (such as the interaction causing a box to attempt to move into a wall).
	 */
	boolean interactAfter(MovingEntity e, Direction d, Cell myCell);
	
	/**
	 * @return Returns an integer with the class' position in the draw hierarchy,
	 * for when multiple entities occupy the same tile.
	 */
	int drawHierarchy();
}
