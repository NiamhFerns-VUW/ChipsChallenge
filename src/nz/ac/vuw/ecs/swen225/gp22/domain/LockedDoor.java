package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Blocks movement until it is unlocked by Chip with a Key of the same colour.
 */
public class LockedDoor extends FreeTile {
	public String lockColour;

	public LockedDoor() {
		this.lockColour = "";
	}
	public LockedDoor(String lockColour) {
		this.lockColour = lockColour;
	}
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		if (!(e instanceof Chip c)) return false;

		Optional<Key> key = c.level.getInventory().stream()
				.filter((t)->{return t instanceof Key k && k.keyColour.equals(lockColour);})
				.map(k -> (Key) k)
				.findFirst();

		if (key.isEmpty()) return false;

		myCell.setStoredTile(new FreeTile());

		return true;
	}

	public String toString() {
		return "D";
	}
}
