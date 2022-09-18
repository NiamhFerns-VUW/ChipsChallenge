package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Info extends FreeTile {
	public String infoText;

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	public Info(Coord coords, String infoText) {
		super(coords);
		this.infoText = infoText;
	}
	public Info(){}
	public Info(String infoText){
		this.infoText = infoText;
	}
	@Override
	public boolean onMoveInto(Entity e, Direction d) {
		throw new Error("Code not done!");	//TODO
	}
}
