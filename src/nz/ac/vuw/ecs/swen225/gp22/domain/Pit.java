package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Pit extends FreeTile{
	public Pit(Coord coords) {
		super(coords);
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
