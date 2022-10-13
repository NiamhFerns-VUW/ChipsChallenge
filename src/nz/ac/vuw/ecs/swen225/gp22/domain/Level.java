package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.CustomMovingEntityService;

import java.util.ArrayList;
import java.util.List;

/**
 * The level keeps track of the board, Chip, Chip's inventory, and the remaining treasures to be picked up.
 */
public class Level {
	private long remainingTreasures;
	public final Cell[][] cells;
	private ArrayList<Entity> inventory;
	public final Chip player;

	private ArrayList<CustomMovingEntityService> mon;

	public final Runnable onWin;
	public final Runnable onDeath;

	private List<Object> objSoundsToPlay;	// renderer checks this in order to play sounds such as picking up keys

	public Level(long remainingTreasures, Cell[][] cells, Chip player, ArrayList<Entity> inventory,
				 ArrayList<CustomMovingEntityService> mon, Runnable onWin, Runnable onDeath) {
		this.remainingTreasures = remainingTreasures;
		this.cells = cells;
		this.player = player;
		this.inventory = inventory;
		this.mon = mon;
		this.onWin = onWin;
		this.onDeath = onDeath;
		this.objSoundsToPlay = new ArrayList<Object>();
	}
	
	public long getRemainingTreasures() {
		return remainingTreasures;
	}
	public void setRemainingTreasures(long newTreasures) {
		remainingTreasures = newTreasures;
	}
	public ArrayList<Entity> getInventory() {
		return inventory;
	}

	public void addSoundToPlay(Object o) {
		objSoundsToPlay.add(o);
	}

	public List<Object> getSoundsToPlay() {
		return objSoundsToPlay;
	}

	public ArrayList<CustomMovingEntityService> getMon() {
		return mon;
	}

	/**
	 * Adds an entity to this level's inventory if the inventory has room, returning false if it does not.
	 *
	 * @param e - The Entity to add to the inventory.
	 * @return boolean - Whether or not the entity was successfully added.
	 */
	public boolean addToInventory(Entity e) {
		if (inventory.size() > 7) return false;

		inventory.add(e);
		return true;
	}
}
