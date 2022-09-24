package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Key implements Entity {
	public String keyColor;
	public String getKeyColor() {
		return keyColor;
	}

	public void setKeyColor(String keyColor) {
		this.keyColor = keyColor;
	}
	public Key(String keyColor) {
		setKeyColor(keyColor);
	}
	public Key(){}
	@Override
	public boolean interact(Entity e, Direction d, Coord c) {
		throw new Error("Code not done!");	//TODO
	}
	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}

}
