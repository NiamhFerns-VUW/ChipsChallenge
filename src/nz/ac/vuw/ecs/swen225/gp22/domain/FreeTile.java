package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gameImages.Img;

import java.awt.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.CustomMovingEntityService;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.DefaultCustomMovingEntityServiceProvider;

/**
 * The FreeTile is an empty tile that doesn't prevent movement.
 */
@JsonTypeInfo(
	use = JsonTypeInfo.Id.CLASS,
	include = JsonTypeInfo.As.PROPERTY,
	property = "type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = Exit.class, name = "exit"),
	@JsonSubTypes.Type(value = ExitLock.class, name = "exitlock"),
	@JsonSubTypes.Type(value = Hazard.class, name = "hazard"),
	@JsonSubTypes.Type(value = IceHazard.class, name = "icehazard"),
	@JsonSubTypes.Type(value = Info.class, name = "info"),
	@JsonSubTypes.Type(value = LockedDoor.class, name = "lockeddoor"),
	@JsonSubTypes.Type(value = Pit.class, name = "pit"),
	@JsonSubTypes.Type(value = Teleporter.class, name = "teleporter"),
	@JsonSubTypes.Type(value = Wall.class, name = "wall")
})
public class FreeTile {
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}

	public boolean afterMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}

	@JsonIgnore
	public Image getImage() {
		return Img.Freetile.image;
	}

	public String toString() {
		return "_";
	}
	@Override
	public int hashCode() {
		return this.toString().hashCode()+this.getClass().toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof FreeTile && obj.hashCode() == this.hashCode();
	}
}
