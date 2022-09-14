package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Teleporter extends FreeTile {
	public Teleporter(Coord coords) {
		super(coords);
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
