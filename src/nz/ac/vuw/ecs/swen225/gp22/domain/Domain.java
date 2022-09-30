package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.persistency.GameSave;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Persistency;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * The domain class is the part of the domain package the other packages interact with.
 * It keeps track of the level and tells the other classes in this package what to do.
 */
public class Domain {
	Level currentLevel = null;
	Persistency persist = new Persistency();
	
	public void update() {
		System.out.println("Domain recieved update!");

		if (!ok()) throw new Error("No current level to update.");

		//throw new Error("Code not done!");	//TODO
	}
	
	public void startLevel(String levelname) {
		System.out.println("Domain starting level '" + levelname + "'!");
		//GameSave save = persist.loadGameSave(Path.of("./src/levels/" + levelname + ".xml"));
		GameSave save = persist.loadGameSave(Path.of("./src/levels/level1.xml"));

		Cell[][] cells = save.getCells();

		long remainingTreasures = Arrays.stream(cells)
				.flatMap(cArray-> Arrays.stream(cArray))
				.flatMap(c->c.getEntities().stream())
				.filter(e -> e instanceof Treasure)
				.count();

		Optional<Chip> player = Arrays.stream(cells)
				.flatMap(cArray -> Arrays.stream(cArray))
				.flatMap(c->c.getEntities().stream())
				.filter(e -> e instanceof Chip)
				.map(e -> (Chip) e)
				.findFirst();

		System.out.println("test");

		if (player.isEmpty()) { throw new Error("No Chip in level!"); }

		Level currentLevel = new Level(remainingTreasures, cells, player.get(), (ArrayList<Entity>) save.getInventory());

		//throw new Error("Code not done!");	//TODO
	}

	public void movePlayer(Direction dir) {
		System.out.println("Domain moving player in direction " + dir + "!");

		if (!ok()) throw new Error("No current level for moving player.");

		currentLevel.player.move(dir);
	}

	public boolean ok() {
		return currentLevel != null;
	}
	
	public Optional<Level> getLevel() {
		if (!ok()) return Optional.empty();

		return Optional.of(currentLevel);
	}
	public void setLevel(Level newLevel) {
		currentLevel = newLevel;
	}
}