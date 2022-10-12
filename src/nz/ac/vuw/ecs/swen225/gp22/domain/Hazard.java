package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;

/**
 * Hazards kill Chip when he moves onto the Hazard's cell unless Chip has an AntiHazard with the same hazardType.
 */
public class Hazard extends FreeTile {
	public String hazardType;
	public Hazard(String hazardType) {
		this.hazardType = hazardType;
	}
	public Hazard(){
		this.hazardType="";
	}
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}

	public boolean afterMoveInto(MovingEntity e, Direction d, Cell myCell) {
		if (e instanceof Chip) {
			e.level.onDeath.run();
			return false;
		}

		return true;
	}

	public String toString() {
		return "H";
	}

	public Image getImage() {
		throw new Error("No Hazard image yet!");
		//return Img.Freetile.image;
	}
}
