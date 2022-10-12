package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;
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
		if (!key.get().keyColour.equals("Green")) e.level.getInventory().remove(key.get());

		return true;
	}

	public String toString() {
		return "D";
	}

	public Image getImage() {
		return switch (lockColour) {
			case "Red" -> Img.RedLockeddoor.image;
			case "Green" -> Img.GreenLockeddoor.image;
			case "Blue" -> Img.BlueLockeddoor.image;
			case "Yellow" -> Img.YellowLockeddoor.image;
			default -> throw new Error("LockedDoor does not have an image for the colour " + lockColour + "!");
		};
	}
}
