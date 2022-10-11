package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * The FreeTile is an empty tile that doesn't prevent movement.
 */
@JsonTypeInfo(use = Id.CLASS)
@JsonSubTypes({
	@Type(Wall.class),
	@Type(Teleporter.class),
	@Type(Info.class),
	@Type(IceHazard.class),
	@Type(Pit.class),
	@Type(LockedDoor.class) })
public class FreeTile {
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}
}
