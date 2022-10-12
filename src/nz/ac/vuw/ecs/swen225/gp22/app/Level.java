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
                g.drawString(levelName, 80, 50);
                g.drawString("Time Remaining: " + GameClock.get().currentLevelTime() / 1000, 50, getHeight() / 2 - 100);
                g.drawString("Keys Remaining: " + GameHandler.get().chipsRemaining(), 50, getHeight() - 250);
            }
        };
        timerPanel.setMaximumSize(new Dimension(200, 480));
        timerPanel.setFocusable(false);

        gameplayPanel.setMaximumSize(new Dimension(440, 480));
        gameplayPanel.setFocusable(false);
        return List.of(gameplayPanel, timerPanel);
    }

    @Override
    public GameState nextLevel() {
        return new Level("level2", domain, new Render());
    }
}