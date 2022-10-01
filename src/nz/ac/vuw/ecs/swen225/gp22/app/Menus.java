package nz.ac.vuw.ecs.swen225.gp22.app;

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

    private List<JButton> buttons;
    @Override
    public List<JPanel> panels() {
        JPanel menuframe = new JPanel();

        menuframe.setLayout(null);
        menuframe.setFocusable(false);
        menuframe.setSize(new Dimension(640, 480));

        JButton startGame = new JButton();
        startGame.setBounds(220, 100, 200, 50);
        startGame.setText("Start Game");
        startGame.addActionListener(l -> GameHandler.get().onLevelChange());

        JButton loadGame = new JButton();
        loadGame.setBounds(220, 175, 200, 50);
        loadGame.setText("Load Game");
        loadGame.addActionListener(l -> {
            GameSave save = Persistency.loadGameSave(Path.of("./src/levels/level1.xml"));
        });

        JButton quitGame = new JButton();
        quitGame.setBounds(220, 250, 200, 50);
        quitGame.setText("Quit Game");
        quitGame.addActionListener(l -> exit(0));

        buttons = List.of(startGame, loadGame, quitGame);

        buttons.forEach(menuframe::add);

        return List.of(menuframe);
    }

    public StartScreen() {
        buttons = new ArrayList<>();
    }

    @Override
    public GameState nextLevel() {
        return new LevelOne("Level One", new Render());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {}
}
