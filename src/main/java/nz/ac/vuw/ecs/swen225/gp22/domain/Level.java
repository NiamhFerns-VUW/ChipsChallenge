package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

/**
 * The level keeps track of the board, Chip, Chip's inventory, and the remaining treasures to be picked up.
 */
public class Level {
	private int remainingTreasures;
	public Cell[][] cells;
	private ArrayList<Entity> inventory;
	public Chip player;
	
	public Level(int remainingTreasures, Cell[][] cells, Chip player) {
		this.remainingTreasures = remainingTreasures;
		this.cells = cells;
		this.player = player;
		this.inventory = new ArrayList<Entity>();
	}
	public Level(int remainingTreasures, Cell[][] cells, Chip player, ArrayList<Entity> inventory) {
		this.remainingTreasures = remainingTreasures;
		this.cells = cells;
		this.player = player;
		this.inventory = inventory;
	}
	public Level() {

	}

	public void setInventory(ArrayList<Entity> inventory) {
		this.inventory = inventory;
	}
	public Chip getPlayer() {
		return player;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public void setPlayer(Chip player) {
		this.player = player;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public int getRemainingTreasures() {
		return remainingTreasures;
	}
	public void setRemainingTreasures(int newTreasures) {
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
