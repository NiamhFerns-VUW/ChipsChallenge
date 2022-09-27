package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The Key entity allows Chip to unlock LockedDoors of the same colour when the key is in the player's inventory.
 */
public record Key(String keyColour) implements Entity {
	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Coord c) {
		return true;
	}
	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Coord c) {
		if (!(e instanceof Chip p)) return true;
		if (p.level.addToInventory(this)) p.level.cells[c.x()][c.y()].removeEntity(this);
		return true;
	}

	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}
}
