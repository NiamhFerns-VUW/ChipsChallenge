package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The Key entity allows Chip to unlock LockedDoors of the same colour when the key is in the player's inventory.
 */
public class Key implements Entity {
	public String keyColour;

	public Key(String keyColour) {
		this.keyColour = keyColour;
	}
	public Key() {
		this.keyColour = "";
	}

	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}
	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Cell myCell) {
		if (!(e instanceof Chip p)) return true;
		if (p.level.addToInventory(this)) myCell.removeEntity(this);
		return true;
	}

	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}

	public String toString() {
		return "k";
	}
}
