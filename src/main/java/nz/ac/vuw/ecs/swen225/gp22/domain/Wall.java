package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The FreeTile is a basic tile that prevents movement onto it.
 */
public class Wall extends FreeTile {
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return false;
	}
}
