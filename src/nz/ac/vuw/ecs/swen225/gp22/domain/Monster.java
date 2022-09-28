package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The monster moves around and kills Chip when they touch.
 */
public class Monster extends MovingEntity{
	Direction facingDir;
	
	public Monster(Direction facingDir) {
		super(facingDir);
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
