package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The Info tile displays text when Chip is standing on it.
 */
public class Info extends FreeTile {
	public final String infoText;
	public Info(Cell myCell, String infoText) {
		super(myCell);
		this.infoText = infoText;
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
