package nz.ac.vuw.ecs.swen225.gp22.domain;

public record AntiHazard(String hazardType) implements Entity {
	@Override
	public boolean interact(Entity e, Direction d, gp22.domain.Coord c) {
		throw new Error("Code not done!");	//TODO
	}
	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}

}
