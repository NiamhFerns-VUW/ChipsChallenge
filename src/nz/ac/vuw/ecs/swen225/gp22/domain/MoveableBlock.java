package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Can be pushed around by moving into it - it blocks movement if it cannot be pushed forwards.
 */
public class MoveableBlock extends MovingEntity {
	public MoveableBlock() {
		super(Direction.None);
	}
	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Coord c) {
		throw new Error("Code not done!");    //TODO
	}
	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Coord c) {
		throw new Error("Code not done!");	//TODO
	}
	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}
}
