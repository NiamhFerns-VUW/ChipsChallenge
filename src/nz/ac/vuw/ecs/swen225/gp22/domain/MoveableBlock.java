package nz.ac.vuw.ecs.swen225.gp22.domain;

public class MoveableBlock extends MovingEntity {
	public MoveableBlock() {
		super(Direction.None);
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
