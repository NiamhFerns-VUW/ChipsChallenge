package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The domain class is the part of the domain package the other packages interact with.
 * It keeps track of the board, the player's inventory, and tells the other classes what to do. 
 * 
 */
public class Domain {
	Level currentLevel;
	//Persistency persist;
	
	public void update() {
		throw new Error("Code not done!");	//TODO
	}
	
	public void startLevel(String levelname) {
		throw new Error("Code not done!");	//TODO
	}
	
	public void movePlayer(Direction dir) {
		throw new Error("Code not done!");	//TODO
	}
	
	public void getLevel() {
		return currentLevel;
	}
	public void setLevel(Level newLevel) {
		currentLevel = newLevel;
	}
}