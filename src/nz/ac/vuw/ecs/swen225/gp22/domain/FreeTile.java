package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The FreeTile is an empty tile that doesn't prevent movement.
 */
public class FreeTile {
	public final Cell myCell;
	
	public FreeTile(Cell myCell) {
		this.myCell = myCell;
	}
	
	public boolean onMoveInto(Entity e, Direction d) {
		return true;
	}
}
