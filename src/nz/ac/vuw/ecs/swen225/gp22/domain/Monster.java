package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The monster moves around and kills Chip when they touch.
 */
public class Monster extends MovingEntity{
	Direction facingDir;

	public Monster() {
		super(Direction.Down, new Coord(-1, -1));
	}
	public Monster(Direction facingDir, Coord c) {
		super(facingDir, c);
	}

	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
		throw new Error("Code not done!"); //TODO
	}
	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Cell myCell) {
		throw new Error("Code not done!");    //TODO
	}
	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}
}
