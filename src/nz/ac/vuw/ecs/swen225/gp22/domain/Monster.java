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
	public boolean interact(Entity e, Direction d, Coord c) {
		throw new Error("Code not done!");	//TODO
	}
	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}
}
