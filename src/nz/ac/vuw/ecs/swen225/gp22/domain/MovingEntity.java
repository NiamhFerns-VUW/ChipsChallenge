package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The MovingEntity is for Entities that can move outside of the cell they're in, such as
 * Chip moving or a block being pushed.
 */
public abstract class MovingEntity implements Entity {
	Direction facingDir;
	Coord coords;
	Level level;

	public MovingEntity(Direction facingDir) {
		this.facingDir = facingDir;
	}
	
	public Direction getDir() {
		return facingDir;
	}

	/**
	 * Attempts to move in the given direction, returning a boolean based on if the movement was a success or not.
	 *
	 * @param d - The direction to move.
	 * @return boolean - Returns false if it did not move, true otherwise.
	 */
	public boolean move(Direction d) {
		facingDir = d;
		Cell nextCell = level.cells[coords.x()+d.x][coords.y()+d.y];

		if (!nextCell.beforeMoveInto(this, d)) return false;

		level.cells[coords.x()][coords.y()].removeEntity(this);
		nextCell.getEntities().add(this);
		nextCell.afterMoveInto(this, d);
		return true;
	}
}
