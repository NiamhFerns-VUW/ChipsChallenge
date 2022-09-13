package gp22.domain;

public class FreeTile {
	public final Coord coords;
	
	public FreeTile(Coord coords) {
		this.coords = coords;
	}
	
	public boolean onMoveInto(Entity e, Direction d) {
		return true;
	}
}
