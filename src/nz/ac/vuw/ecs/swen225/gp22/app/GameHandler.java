package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameHandler extends JFrame implements Observer {
    private GameState state;

    Domain domain;

    public GameHandler() throws InterruptedException {
        // assert SwingUtilities.isEventDispatchThread();
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setVisible(true);

        start();
        GameClock.get().start();
        while(GameClock.get().isRunning()) {
            // Main Game loop.
        }
    }

    public void paintComponent(Graphics g) {

    }

    public void start() {
        GameClock.get().register(this);
    }

    @Override
    public void update() {
        System.out.println("Hello: " + GameClock.get().currentTick());
    }
}
