package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The IceHazard causes Chip to slip and move off the ice in the same direction he moved on, unless he has an
 * AntiHazard with the same hazardType.
 */
public class IceHazard extends Hazard {
	public IceHazard() {
		super("Ice");
	}
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}

	@Override
	public boolean afterMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return e.move(d);
	}
}
