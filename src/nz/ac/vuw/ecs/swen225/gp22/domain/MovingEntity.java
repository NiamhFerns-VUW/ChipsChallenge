package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The MovingEntity is for Entities that can move outside of the cell they're in, such as
 * Chip moving or a block being pushed.
 */
public abstract class MovingEntity implements Entity {
	private Direction facingDir;
	public Coord coords;
	@JsonIgnore
	public Level level;

	public MovingEntity(Direction facingDir, Coord coords) {
		this.facingDir = facingDir;
		this.coords = coords;
	}

	public Coord getCoords() {
		return coords;
	}

	public void setCoords(Coord coords) {
		this.coords = coords;
	}

	public Direction getFacingDir() {
		return facingDir;
	}

	public void setFacingDir(Direction facingDir) {
		this.facingDir = facingDir;
	}

	/**
	 * Attempts to move in the given direction, returning a boolean based on if the movement was a success or not.
	 *
	 * @param d - The direction to move.
	 * @return boolean - Returns false if it did not move, true otherwise.
	 */
	public boolean move(Direction d) {
		facingDir = d;
		Cell nextCell = level.cells[coords.x()+d.y][coords.y()+d.x];

		if (!nextCell.beforeMoveInto(this, d)) return false;

		level.cells[coords.x()][coords.y()].removeEntity(this);
		coords = new Coord(coords.x()+d.y, coords.y()+d.x);
		nextCell.getEntities().add(this);
		nextCell.afterMoveInto(this, d);

		return true;
	}
	@Override
	public int hashCode() {
		return coords.hashCode()+facingDir.hashCode(); // Ignores level because I can't serialize
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MovingEntity && obj.hashCode() == this.hashCode();
	}
}
