package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The Entity interface is for the various things that the player could interact with.
 * Different from tiles, these can stack on top of each other (for example, a monster standing on the key, or a box pushed onto treasure).
 * 
 */
public interface Entity {
	/**
	 * The interact method causes the entity to perform an action based on its class.
	 * 
	 * @param e - The Entity interacting with this Entity.
	 * @param d - The direction the interacting Entity is moving.
	 * @param c - The current coordinates of this entity.
	 * 
	 * @return Returns true if the interaction is successful, false if there is a failure
	 * (such as the interaction causing a box to attempt to move into a wall).
	 */
	boolean interact(Entity e, Direction d, Coord c);
	
	/**
	 * @return Returns an integer with the class' position in the draw hierarchy,
	 * for when multiple entities occupy the same tile.
	 */
	int drawHierarchy();
}
