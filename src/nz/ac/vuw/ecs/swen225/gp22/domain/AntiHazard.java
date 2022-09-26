package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The AntiHazard is an Entity that, when in the player's inventory, nullifies the effect of the Hazard with the
 * corresponding HazardType.
 *
 * @param hazardType - Hazards with the same hazardType are cancelled out.
 */
public record AntiHazard(String hazardType) implements Entity {
	@Override
	public boolean interact(Entity e, Direction d, Coord c) {
		throw new Error("Code not done!");    //TODO
	}

	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}

}
