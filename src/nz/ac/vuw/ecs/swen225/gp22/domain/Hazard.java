package gp22.domain;

public class Hazard extends FreeTile {
	public final String hazardType;
	public Hazard(Coord coords, String hazardType) {
		super(coords);
		this.hazardType = hazardType;
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
