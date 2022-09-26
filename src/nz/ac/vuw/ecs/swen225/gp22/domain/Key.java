package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The Key entity allows Chip to unlock LockedDoors of the same colour when the key is in the player's inventory.
 */
public record Key(String keyColour) implements Entity {
	@Override
	public boolean interact(Entity e, Direction d, Coord c) {
		throw new Error("Code not done!");	//TODO
	}
	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}

}
