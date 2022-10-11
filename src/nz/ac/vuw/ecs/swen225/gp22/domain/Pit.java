package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;

/**
 * Blocks movement across until a MoveableBlock is pushed onto it, removing the block and filling the pit.
 */
public class Pit extends FreeTile{
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		if (e instanceof MoveableBlock) return true;

		return false;
	}

	@Override
	public boolean afterMoveInto(MovingEntity e, Direction d, Cell myCell) {
		if (e instanceof Chip) {
			e.level.onDeath.run();
			return false;
		}
		else if (!(e instanceof MoveableBlock)) throw new Error("Only MoveableBlocks can move onto pits!");

		myCell.removeEntity(e);
		myCell.setStoredTile(new FreeTile());
		return true;
	}

	public String toString() {
		return "P";
	}

	public Image getImage() {
		throw new Error("No image for Pit yet!");

		//return Img.Freetile.image;
	}
}