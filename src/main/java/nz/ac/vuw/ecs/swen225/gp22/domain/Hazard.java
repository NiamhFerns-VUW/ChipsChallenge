package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Hazards kill Chip when he moves onto the Hazard's cell unless Chip has an AntiHazard with the same hazardType.
 */
public class Hazard extends FreeTile {
	public final String hazardType;
	public Hazard(String hazardType) {
		this.hazardType = hazardType;
	}
	public Hazard(){
		this.hazardType=null;
	}
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		throw new Error("Code not done!");	//TODO
	}
}
