package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The FreeTile is a basic tile that prevents movement onto it.
 */
public class Wall extends FreeTile {
//	private String name = "Wall";
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String type) {
//		this.name = type;
//	}

	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return false;
	}
}
