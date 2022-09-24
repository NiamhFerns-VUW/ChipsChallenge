package nz.ac.vuw.ecs.swen225.gp22.domain;

public class LockedDoor extends FreeTile {

	public String doorColor;

	public LockedDoor(String doorColor) {
		super();
		this.doorColor = doorColor;
	}
	public LockedDoor(){}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
