package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The IceHazard causes Chip to slip and move off the ice in the same direction he moved on, unless he has an
 * AntiHazard with the same hazardType.
 */
public class IceHazard extends Hazard {
	public IceHazard(Cell myCell) {
		super(myCell, "Ice");
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
