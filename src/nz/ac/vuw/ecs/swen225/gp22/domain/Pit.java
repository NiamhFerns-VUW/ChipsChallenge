package nz.ac.vuw.ecs.swen225.gp22.domain;

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
		if (!(e instanceof MoveableBlock)) throw new Error("Only MoveableBlocks can move onto pits!");

		myCell.removeEntity(e);
		myCell.setStoredTile(new FreeTile());
		return true;
	}

	public String toString() {
		return "P";
	}
}