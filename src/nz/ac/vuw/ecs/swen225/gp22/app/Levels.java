package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Render;

import javax.swing.*;
import java.awt.*;
import java.util.List;

interface Level {
    String levelName();
    Render gameplayPanel();
}

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
