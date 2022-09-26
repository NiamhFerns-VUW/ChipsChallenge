package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The ExitLock prevents Chip moving past until all Treasures have been collected.
 */
public class ExitLock extends FreeTile {
	public ExitLock(Cell myCell) {
		super(myCell);
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
