package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The FreeTile is an empty tile that doesn't prevent movement.
 */
public class FreeTile {
	private String name;
	public FreeTile() {
		setName("FreeTile");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}
}
