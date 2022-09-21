package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Wall extends FreeTile {
	public Wall(Coord coords) {
		super(coords);
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		return false;
	}
}
