package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The AntiHazard is an Entity that, when in the player's inventory, nullifies the effect of the Hazard with the
 * corresponding HazardType.
 *
 * @param hazardType - Hazards with the same hazardType are cancelled out.
 */
public record AntiHazard(String hazardType) implements Entity {
	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}
	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Cell myCell) {
		if (!(e instanceof Chip p)) return true;
		if (p.level.addToInventory(this)) myCell.removeEntity(this);
		return true;
	}

	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}

}
