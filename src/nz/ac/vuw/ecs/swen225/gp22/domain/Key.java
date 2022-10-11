package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;

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
		return 3;
	}

	public String toString() {
		return "k";
	}

	public Image getImage() {
		return switch (keyColour) {
			case "Red" -> Img.Redkey.image;
			case "Green" -> Img.Greenkey.image;
			case "Blue" -> Img.Bluekey.image;
			case "Yellow" -> Img.Yellowkey.image;
			default -> throw new Error("Key does not have an image for the colour " + keyColour + "!");
		};
	}
}
