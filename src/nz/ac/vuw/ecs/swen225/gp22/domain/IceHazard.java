package gp22.domain;

public class IceHazard extends Hazard {
	public IceHazard(Coord coords) {
		super(coords, "Ice");
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
