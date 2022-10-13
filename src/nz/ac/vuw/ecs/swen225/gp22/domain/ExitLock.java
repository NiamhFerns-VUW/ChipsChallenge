package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;

/**
 * The ExitLock prevents Chip moving past until all Treasures have been collected.
 */
public class ExitLock extends FreeTile {
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		if (e.level.getRemainingTreasures() == 0 && e instanceof Chip) return true;

		return false;
	}

	public boolean afterMoveInto(MovingEntity e, Direction d, Cell myCell) {
		if (!(e instanceof Chip) || e.level.getRemainingTreasures() != 0){
			throw new Error("Only Chip can move onto exit locks when no treasures are left!");
		}
		e.level.addSoundToPlay(this);
		myCell.setStoredTile(new FreeTile());
		return true;
	}

	public String toString() {
		return "L";
	}

	public Image getImage() {
		return Img.Exitlock.image;
	}
}
