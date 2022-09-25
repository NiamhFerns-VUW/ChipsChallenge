package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The level keeps track of the board, Chip, Chip's inventory, and the remaining treasures to be picked up.
 */
public class Level {
	private int remainingTreasures;
	public final Cell[][] cells;
	private Entity[] inventory;
	public final Chip player;
	
	public Level(int remainingTreasures, Cell[][] cells, Chip player) {
		this.remainingTreasures = remainingTreasures;
		this.cells = cells;
		this.player = player;
		this.inventory = new Entity[8];
	}
	public Level(int remainingTreasures, Cell[][] cells, Chip player, Entity[] inventory) {
		this.remainingTreasures = remainingTreasures;
		this.cells = cells;
		this.player = player;
		this.inventory = inventory;
	}
	
	public int getRemainingTreasures() {
		return remainingTreasures;
	}
	public void setRemainingTreasures(int newTreasures) {
		remainingTreasures = newTreasures;
	}
	public Entity[] getInventory() {
		return inventory;
	}
}
