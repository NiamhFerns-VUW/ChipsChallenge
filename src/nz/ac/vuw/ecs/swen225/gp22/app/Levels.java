package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Render;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents a level of chip's challenge.
 *
 * @author niamh
 */
interface Level {
    String levelName();
    Render gameplayPanel();
}

// ------------------------------------------------
// NEEDS MAJOR REFACTORING TO REMOVE DUPLICATE CODE
// You should not need an interface here. A single
// record called "Level" should be enough.
// ------------------------------------------------

/**
 * Represents level one of chip's challenge.
 *
 * @author niamh
 */
record LevelOne(String levelName, Render gameplayPanel) implements GameState, Level {
    @Override
    public List<JPanel> panels() {
        JPanel timerPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        timerPanel.setMaximumSize(new Dimension(640, 75));
        timerPanel.setFocusable(false);
        gameplayPanel.setMaximumSize(new Dimension(640, 405));
        gameplayPanel.setFocusable(false);
        return List.of(timerPanel, gameplayPanel);
    }

    @Override
    public GameState nextLevel() {
        return new LevelTwo("Level Two", new Render());
    }
}

/**
 * Represents level two of chip's challenge.
 *
 * @author niamh
 */
record LevelTwo(String levelName, Render gameplayPanel) implements GameState, Level {
    @Override
    public List<JPanel> panels() {
        return null;
    }

    @Override
    public GameState nextLevel() {
        return null;
    }
}
