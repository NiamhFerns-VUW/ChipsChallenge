package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Blocks movement until it is unlocked by Chip with a Key of the same colour.
 */
public class LockedDoor extends FreeTile {
	public final String lockColour;
	public LockedDoor(Cell myCell, String lockColour) {
		super(myCell);
		this.lockColour = lockColour;
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
