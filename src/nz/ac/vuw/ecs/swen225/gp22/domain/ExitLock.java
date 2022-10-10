package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The ExitLock prevents Chip moving past until all Treasures have been collected.
 */
public class ExitLock extends FreeTile {
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return false;
	}

	public String toString() {
		return "L";
	}
}
