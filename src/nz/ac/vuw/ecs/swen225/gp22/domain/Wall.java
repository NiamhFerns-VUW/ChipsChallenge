package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The FreeTile is a basic tile that prevents movement onto it.
 */
public class Wall extends FreeTile {
	public Wall(Cell myCell) {
		super(myCell);
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		return false;
	}
}
