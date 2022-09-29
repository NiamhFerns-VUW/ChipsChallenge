package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Persistency;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Render;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

record StartScreen(String levelName) implements GameState {
    @Override
    public Runnable action(Domain d) {
        return () -> {};
    }

    @Override
    public List<JPanel> panels() {
        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        // setJMenuBar(p);
        p.setMaximumSize(new Dimension(640, 480));
        return List.of(p);
    }

    @Override
    public GameState nextLevel() {
        return new LevelOne("Level One", new Render());
    }
}
