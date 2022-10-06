package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The MovingEntity is for Entities that can move outside of the cell they're in, such as
 * Chip moving or a block being pushed.
 */
public abstract class MovingEntity implements Entity {
	Direction facingDir;
	public Coord coords;
	public Level level;

	public MovingEntity(Direction facingDir, Coord coords) {
		this.facingDir = facingDir;
		this.coords = coords;
	}

	public Direction getFacingDir() {
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
