package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Blocks movement across until a MoveableBlock is pushed onto it, removing the block and the pit.
 */
public class Pit extends FreeTile{
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		throw new Error("Code not done!");	//TODO
	}
}
