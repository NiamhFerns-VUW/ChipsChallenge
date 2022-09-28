package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The Info tile displays text when Chip is standing on it.
 */
public class Info extends FreeTile {
	public final String infoText;
	public Info(String infoText) {
		this.infoText = infoText;
	}
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		throw new Error("Code not done!");	//TODO
	}
}
