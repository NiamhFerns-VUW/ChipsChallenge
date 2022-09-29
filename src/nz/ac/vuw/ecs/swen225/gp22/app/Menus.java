package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;
import nz.ac.vuw.ecs.swen225.gp22.persistency.GameSave;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Persistency;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

class StartScreen implements GameState, ActionListener {
    private final String levelName;

    private List<JButton> buttons;

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Runnable action(Domain d) {
        return () -> {};
    }

    @Override
    public List<JPanel> panels() {
        JPanel menuframe = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
//                g.setColor(Color.GRAY);
//                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        menuframe.setLayout(null);
        menuframe.setSize(new Dimension(640, 480));

        JButton startGame = new JButton();
        startGame.setBounds(220, 100, 200, 50);
        startGame.addActionListener(l -> GameHandler.get().onLevelChange());
        startGame.setText("Start Game");

        JButton loadGame = new JButton();
        loadGame.setBounds(220, 175, 200, 50);
        loadGame.addActionListener(l -> {
            GameSave save = Persistency.loadGameSave(Path.of("./src/levels/level1.xml"));
            System.out.println(save.getClass().getSimpleName());
        });
        loadGame.setText("Load Game");

        JButton quitGame = new JButton();
        quitGame.setBounds(220, 250, 200, 50);
        quitGame.addActionListener(l -> exit(0));
        quitGame.setText("Quit Game");

        buttons = List.of(startGame, loadGame, quitGame);

        buttons.forEach(menuframe::add);

        return List.of(menuframe);
    }

    public StartScreen(String levelName) {
        this.levelName = levelName;
        buttons = new ArrayList<>();
    }

    @Override
    public GameState nextLevel() {
        return new LevelOne("Level One", new Render());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {}
}
