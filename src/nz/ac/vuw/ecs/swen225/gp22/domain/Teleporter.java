package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Teleporters teleport the target to a random different teleporter of the same colour when they are stepped on.
 */
public class Teleporter extends FreeTile {
	private String type = "Teleporter";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		throw new Error("Code not done!");	//TODO
	}
}
