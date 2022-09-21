package nz.ac.vuw.ecs.swen225.gp22.domain;

public class LockedDoor extends FreeTile {
	public LockedDoor(Coord coords) {
		super(coords);
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
