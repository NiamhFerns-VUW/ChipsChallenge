package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Render;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
record Level(String levelName, Domain domain, Render gameplayPanel) implements GameState {
    @Override
    public List<JPanel> panels() {
        JPanel timerPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.GREEN);
                g.drawString(levelName, 20, getHeight() / 2);
                g.drawString(GameClock.get().currentLevelTime() / 1000 + "", getWidth() / 2, getHeight() / 2);
                g.drawString("Chips Level: " + 0, getWidth() - 100, getHeight() / 2);
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
        return new Level("Level2", domain, new Render());
    }
}