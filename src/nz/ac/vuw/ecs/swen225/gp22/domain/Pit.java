package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Blocks movement across until a MoveableBlock is pushed onto it.
 */
public class Pit extends FreeTile{
	public Pit(Cell myCell) {
		super(myCell);
	}
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
