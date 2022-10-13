package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.persistency.Persistency;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.GameSave;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Persistency;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.custom.CustomMovingEntityService;
import nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.GameSaveHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.GameSaveHelper.getLevel1GameSave;
import static nz.ac.vuw.ecs.swen225.gp22.persistency2.helpers.GameSaveHelper.getLevel2GameSave;

/**
 * The domain class is the part of the domain package the other packages interact with.
 * It keeps track of the level and tells the other classes in this package what to do.
 */
public class Domain {
	Level currentLevel = null;
	Persistency persist = new Persistency();

	int wait = 10;
	
	public void update() {
		//System.out.println("Domain recieved update!");
		if (!ok()) throw new IllegalStateException("No current level to update.");

		if (wait-- > 0) return;

		wait = 10;

		currentLevel.getMon().stream().forEach((m)->{
			m.move(m.getNextDirection());
		});
	}

	/**
	 * startLevel takes a file path, asks Persistency for the corresponding level, and then converts the
	 * resulting GameSave into a useable level using createLevel
	 *
	 * @param levelname - the path to the level
	 */
	public void startLevel(String levelname, Runnable onWin, Runnable onDeath) {
		try {
			GameSave save = GameSaveHelper.loadGameSave(Path.of("./levels/" + levelname + ".xml"));

			Cell[][] cells = new Cell[save.CELLS_HEIGHT][save.CELLS_WIDTH];

			save.getCellList().forEach((c) -> {
				cells[c.getCoord().y()][c.getCoord().x()] = c;
			});
			createLevel(cells, new ArrayList<Entity>(save.getInventory()), onWin, onDeath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void createLevel(Cell[][] cells, ArrayList<Entity> inventory, Runnable onWin, Runnable onDeath) {
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
				.reduce((c1, c2)->{throw new IllegalArgumentException("Too many Chips in level!");});


		List<MovingEntity> movingEntityList = new ArrayList<>();
		ArrayList<CustomMovingEntityService> monsters = new ArrayList<>();

		IntStream.range(0, cells.length)
				.forEach((y)->{
					IntStream.range(0, cells[y].length)
							.forEach((x)->{
								cells[y][x].getEntities().stream()
										.filter(e -> e instanceof MovingEntity)
										.map(e -> (MovingEntity) e)
										.forEach(m -> {
											m.coords = new Coord(y, x);
											movingEntityList.add(m);
											if (m instanceof CustomMovingEntityService mon) monsters.add(mon);
											});
							});
				});

		if (player.isEmpty()) { throw new IllegalArgumentException("No Chip in level!"); }

		currentLevel = new Level(remainingTreasures, cells, player.get(), inventory, monsters, onWin, onDeath);

		player.get().setLevel(currentLevel);

		movingEntityList.stream().forEach(m -> m.level = currentLevel);
	}

	public void movePlayer(Direction dir) {
		if (!ok()) throw new IllegalStateException("No current level for moving player.");

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