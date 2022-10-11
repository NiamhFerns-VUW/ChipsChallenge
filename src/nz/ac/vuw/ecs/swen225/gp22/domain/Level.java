package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;

/**
 * The level keeps track of the board, Chip, Chip's inventory, and the remaining treasures to be picked up.
 */
public class Level {
	private long remainingTreasures;
	public final Cell[][] cells;
	private ArrayList<Entity> inventory;
	public final Chip player;

	public Level(long remainingTreasures, Cell[][] cells, Chip player, ArrayList<Entity> inventory) {
		this.remainingTreasures = remainingTreasures;
		this.cells = cells;
		this.player = player;
		this.inventory = inventory;
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
