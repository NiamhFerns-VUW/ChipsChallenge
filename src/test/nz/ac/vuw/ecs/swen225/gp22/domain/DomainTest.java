package nz.ac.vuw.ecs.swen225.gp22.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {

    @Test
    void createLevel1() {
        Domain dom = new Domain();
        Cell[][] cells = {{new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall())},
                            {new Cell(new Wall()), new Cell(new FreeTile()), new Cell(new FreeTile()), new Cell(new Wall())},
                            {new Cell(new Wall()), new Cell(new FreeTile()), new Cell(new FreeTile()), new Cell(new Wall())},
                            {new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall())}};

        cells[2][2].getEntities().add(new Chip());

        dom.createLevel(cells, new ArrayList<Entity>());

        if (dom.getLevel().isPresent()) {
            System.out.println(levelToString(dom.getLevel().get()));
        }
    }






    public static String levelToString(Level level) {
        List<String> levelString = new ArrayList<>();

        levelString.add(Arrays.stream(level.cells).map(cArray -> Arrays.stream(cArray)
                .map(c -> c.getStoredTile().toString()).reduce("", (s1, s2)->{return s1 + "|" + s2;}) + "|"
        ).reduce("", (s1, s2) -> {return s1 + "\n" + s2;}));

        return levelString.get(0);
    }
}