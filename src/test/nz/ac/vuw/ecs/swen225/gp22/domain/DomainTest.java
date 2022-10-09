package nz.ac.vuw.ecs.swen225.gp22.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {
    // ==========================
    //       Valid Tests:
    // ==========================

    // ==| Creating the Level |==
    @Test
    void createLevel1() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(4,4);
        cells[2][2].getEntities().add(new Chip());

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");
        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|\n"+
                        "|W|_|_|W|\n"+
                        "|W|_|_|W|\n"+
                        "|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|\n"+
                        "|_|_|_|_|\n"+
                        "|_|_|c|_|\n"+
                        "|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }
    @Test
    void createLevel2() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(4,4);
        cells[1][1].getEntities().add(new AntiHazard());
        cells[1][2].getEntities().add(new Key());
        cells[2][1].getEntities().add(new Treasure());
        cells[2][2].getEntities().add(new Chip());

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");
        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|\n"+
                        "|W|_|_|W|\n"+
                        "|W|_|_|W|\n"+
                        "|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|\n"+
                        "|_|a|k|_|\n"+
                        "|_|t|c|_|\n"+
                        "|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 1;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }

    @Test
    void createLevel3() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(5,5);
        cells[2][2].getEntities().add(new Chip());
        cells[1][1].setStoredTile(new LockedDoor());
        cells[1][2].setStoredTile(new Pit());
        cells[1][3].setStoredTile(new Info());
        cells[2][1].setStoredTile(new Hazard());
        cells[3][1].setStoredTile(new Exit());
        cells[3][2].setStoredTile(new ExitLock());
        cells[3][3].setStoredTile(new Teleporter());

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");
        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|W|\n"+
                        "|W|D|P|I|W|\n"+
                        "|W|H|_|_|W|\n"+
                        "|W|E|L|T|W|\n"+
                        "|W|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|c|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }

    @Test
    void createLevel4() {
        Domain dom = new Domain();
        dom.startLevel("./src/levels/level1.xml");

        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");
        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                """
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
                |_|_|_|W|W|W|W|W|_|W|W|W|W|W|_|_|_|
                |_|_|_|W|_|_|_|W|W|W|_|_|_|W|_|_|_|
                |_|_|_|W|_|_|_|W|E|W|_|_|_|W|_|_|_|
                |_|W|W|W|W|W|D|W|L|W|D|W|W|W|W|W|_|
                |_|W|_|_|_|D|_|_|_|_|_|D|_|_|_|W|_|
                |_|W|_|_|_|W|_|_|I|_|_|W|_|_|_|W|_|
                |_|W|W|W|W|W|_|_|_|_|_|W|W|W|W|W|_|
                |_|W|_|_|_|W|_|_|_|_|_|W|_|_|_|W|_|
                |_|W|_|_|_|D|_|_|_|_|_|D|_|_|_|W|_|
                |_|W|W|W|W|W|W|D|W|D|W|W|W|W|W|W|_|
                |_|_|_|_|_|W|_|_|W|_|_|W|_|_|_|_|_|
                |_|_|_|_|_|W|_|_|W|_|_|W|_|_|_|_|_|
                |_|_|_|_|_|W|_|_|W|_|_|W|_|_|_|_|_|
                |_|_|_|_|_|W|W|W|W|W|W|W|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|""");
        expectedLevel.add(
                "\n" +
                """
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
                |_|_|_|_|_|t|_|_|_|_|_|t|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
                |_|_|_|k|_|_|_|_|_|_|_|_|_|k|_|_|_|
                |_|_|_|t|_|_|k|_|_|_|k|_|_|t|_|_|_|
                |_|_|_|_|_|_|t|_|c|_|t|_|_|_|_|_|_|
                |_|_|_|t|_|_|k|_|_|_|k|_|_|t|_|_|_|
                |_|_|_|_|_|_|_|_|t|_|_|_|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
                |_|_|_|_|_|_|_|t|_|t|_|_|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|k|_|_|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
                |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|""");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 11;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }

    // ==| Chip Tests |==
    @Test
    void moveChip1() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(4,4);
        cells[2][2].getEntities().add(new Chip());

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Up);
        dom.movePlayer(Direction.Left);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|\n"+
                        "|W|_|_|W|\n"+
                        "|W|_|_|W|\n"+
                        "|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|\n"+
                        "|_|c|_|_|\n"+
                        "|_|_|_|_|\n"+
                        "|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }
    @Test
    void moveChip2() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(4,4);
        cells[1][1].getEntities().add(new Chip());

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Down);
        dom.movePlayer(Direction.Right);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|\n"+
                        "|W|_|_|W|\n"+
                        "|W|_|_|W|\n"+
                        "|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|\n"+
                        "|_|_|_|_|\n"+
                        "|_|_|c|_|\n"+
                        "|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }
    @Test
    void pickupEntities1() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(5,5);
        cells[2][2].getEntities().add(new Chip());

        cells[2][1].getEntities().add(new Key("Blue Key"));
        cells[1][1].getEntities().add(new AntiHazard("Fire"));
        cells[1][2].getEntities().add(new Treasure());

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Up);
        dom.movePlayer(Direction.Left);
        dom.movePlayer(Direction.Down);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|c|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        expectedInventory.add("a:Fire");
        expectedInventory.add("k:Blue Key");
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }

    @Test
    void moveBlock1() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(5,5);
        cells[1][1].getEntities().add(new Chip());

        cells[2][2].getEntities().add(new MoveableBlock());

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Down);
        dom.movePlayer(Direction.Right);
        dom.movePlayer(Direction.Down);
        dom.movePlayer(Direction.Right);
        dom.movePlayer(Direction.Up);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|b|_|\n"+
                        "|_|_|_|c|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }
    @Test
    void moveBlockIntoPit1() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(5,5);
        cells[1][1].getEntities().add(new Chip());

        cells[2][2].getEntities().add(new MoveableBlock());

        cells[2][3].setStoredTile(new Pit());

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Down);
        dom.movePlayer(Direction.Right);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|c|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }
    @Test
    void openLockedDoor1() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(5,5);
        cells[1][1].getEntities().add(new Chip());

        cells[2][2].getEntities().add(new Key("Blue"));

        cells[2][3].setStoredTile(new LockedDoor("Blue"));

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Down);
        dom.movePlayer(Direction.Right);
        dom.movePlayer(Direction.Right);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|c|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        expectedInventory.add("k:Blue");
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }

    @Test
    void openLockedDoor2() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(5,5);
        cells[1][1].getEntities().add(new Chip());

        cells[2][2].getEntities().add(new Key("Blue"));
        cells[2][1].getEntities().add(new Key("Blue"));

        cells[2][3].setStoredTile(new LockedDoor("Blue"));

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Down);
        dom.movePlayer(Direction.Right);
        dom.movePlayer(Direction.Right);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|c|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        expectedInventory.add("k:Blue");
        expectedInventory.add("k:Blue");
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }

    @Test
    void teleportChip1() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(5,5);
        cells[2][2].getEntities().add(new Chip());

        cells[2][1].setStoredTile(new Teleporter("Blue"));
        cells[2][3].setStoredTile(new Teleporter("Blue"));

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Right);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|T|_|T|W|\n"+
                        "|W|_|_|_|W|\n"+
                        "|W|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|c|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }

    @Test
    void teleportChip2() {
        Domain dom = new Domain();
        Cell[][] cells = createTestCells(5,5);
        cells[2][2].getEntities().add(new Chip());

        cells[2][1].setStoredTile(new Teleporter("Blue"));
        cells[2][3].setStoredTile(new Teleporter("Blue"));
        cells[1][2].setStoredTile(new Teleporter("Red"));
        cells[3][3].setStoredTile(new Teleporter("Red"));

        dom.createLevel(cells, new ArrayList<Entity>());
        if (dom.getLevel().isEmpty()) throw new Error("Missing level!");

        dom.movePlayer(Direction.Left);
        dom.movePlayer(Direction.Down);

        List<String> levelSlices = levelToStrings(dom.getLevel().get());
        List<String> actualInventory = inventoryToStrings(dom.getLevel().get());

        List<String> expectedLevel = new ArrayList<String>();
        expectedLevel.add(
                "\n" +
                        "|W|W|W|W|W|\n"+
                        "|W|_|T|_|W|\n"+
                        "|W|T|_|T|W|\n"+
                        "|W|_|_|T|W|\n"+
                        "|W|W|W|W|W|");
        expectedLevel.add(
                "\n" +
                        "|_|_|_|_|_|\n"+
                        "|_|_|c|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|\n"+
                        "|_|_|_|_|_|");
        List<String> expectedInventory = new ArrayList<String>();
        int expectedTreasure = 0;

        assertEquals(expectedLevel, levelSlices);
        assertEquals(expectedInventory, actualInventory);
        assertEquals(expectedTreasure, dom.getLevel().get().getRemainingTreasures());
    }


    public static Cell[][] createTestCells(int row, int col) {
        Cell[][] cells = new Cell[row][col];

        IntStream.range(0, row).forEach(yNum -> {
            IntStream.range(0, col).forEach(xNum -> {
               if (yNum == 0 || yNum == row-1 || xNum == 0 || xNum == col-1) cells[yNum][xNum] = new Cell(new Wall());
               else cells[yNum][xNum] = new Cell(new FreeTile());
            });
        });

        return cells;
    }

    public static List<String> levelToStrings(Level level) {
        List<String> levelString = new ArrayList<String>();

        int levelSlices = Arrays.stream(level.cells)
                        .flatMap(cellArray -> Arrays.stream(cellArray))
                        .mapToInt(c -> c.getEntities().size())
                        .reduce(0, (a,b)->{return a > b ? a : b;});

        levelString.add(Arrays.stream(level.cells).map(cArray -> Arrays.stream(cArray)
                .map(c -> c.getStoredTile().toString()).reduce("", (s1, s2)->{
                    return s1 + "|" + s2;
                }) + "|"
        ).reduce("", (s1, s2) -> {
            return s1 + "\n" + s2;
        }));

        for (int i = 0; i < levelSlices; i++) {
            int finalI = i;
            levelString.add(Arrays.stream(level.cells).map(cArray -> Arrays.stream(cArray)
                    .map((c)->{
                        if (finalI < c.getEntities().size()) return c.getEntities().get(finalI);
                        return "_";
                    })
                    .reduce("", (s1, s2) -> {
                        return s1 + "|" + s2;
                    }) + "|"
            ).reduce("", (s1, s2) -> {
                return s1 + "\n" + s2;
            }));
        }

        return levelString;
    }

    public static List<String> inventoryToStrings(Level level) {
        return level.getInventory().stream()
                .map((e)->{
                        String str = e.toString();
                        if (e instanceof AntiHazard n) str += ":" + n.hazardType;
                        else if (e instanceof Key n) str += ":" + n.keyColour;
                        return str;
                })
                .toList();
    }
}