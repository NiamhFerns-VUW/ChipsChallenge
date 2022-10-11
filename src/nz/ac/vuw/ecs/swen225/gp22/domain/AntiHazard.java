package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;

/**
 * The AntiHazard is an Entity that, when in the player's inventory, nullifies the effect of the Hazard with the
 * corresponding HazardType.
 *
 * @param hazardType - Hazards with the same hazardType are cancelled out.
 */
public class AntiHazard implements Entity {
	public String hazardType;

	public AntiHazard(String hazardType) {
		this.hazardType = hazardType;
	}
	public AntiHazard() {
		this.hazardType = "";
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
		return 5;
	}

	public String toString() {
		return "a";
	}

	public Image getImage() {
		throw new Error("No image for Antihazard!");

		//return Img.Exitlock.image;
	}
}
