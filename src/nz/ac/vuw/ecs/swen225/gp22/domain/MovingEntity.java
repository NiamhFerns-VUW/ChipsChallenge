package gp22.domain;

public abstract class MovingEntity implements Entity {
	Direction facingDir;
	Coord coords;
	Level level;
	
	public MovingEntity(Direction facingDir) {
		this.facingDir = facingDir;
	}
	
	public Direction getDir() {
		return facingDir;
	}
	
	public void move(Direction d) {
		throw new Error("Code not done!");	//TODO
	}
	
	public abstract boolean interact(Entity e, Direction d, Coord c);
}
