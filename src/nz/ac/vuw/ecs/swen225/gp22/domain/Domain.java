package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Optional;

/**
 * The domain class is the part of the domain package the other packages interact with.
 * It keeps track of the level and tells the other classes in this package what to do.
 */
public class Domain {
	Level currentLevel = null;
	//Persistency persist;
	
	public void update() {
		System.out.println("Domain recieved update!");

		if (currentLevel == null) throw new Error("No current level to update.");

		//throw new Error("Code not done!");	//TODO
	}
	
	public void startLevel(String levelname) {
		System.out.println("Domain starting level '" + levelname + "'!");
		//throw new Error("Code not done!");	//TODO
	}

	public void movePlayer(Direction dir) {
		System.out.println("Domain moving player!");

		if (currentLevel == null) throw new Error("No current level for moving player.");

		currentLevel.player.move(dir);
	}
	
	public Optional<Level> getLevel() {
		if (currentLevel == null) return Optional.empty();

		return Optional.of(currentLevel);
	}
	public void setLevel(Level newLevel) {
		currentLevel = newLevel;
	}
}