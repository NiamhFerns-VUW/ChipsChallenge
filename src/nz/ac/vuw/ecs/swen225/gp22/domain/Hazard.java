package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Hazards kill Chip when he moves onto the Hazard's cell unless Chip has an AntiHazard with the same hazardType.
 */
public class Hazard extends FreeTile {
	public final String hazardType;
	public Hazard(Cell myCell, String hazardType) {
		super(myCell);
		this.hazardType = hazardType;
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
