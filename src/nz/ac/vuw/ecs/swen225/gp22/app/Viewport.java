package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class Viewport extends JFrame implements Observer {
    private GameState state;
    private final InputHandler input;

    private List<JPanel> panels;

    Runnable onLevelChange;
    Runnable onStart;

    public Viewport(InputHandler input) {
        assert SwingUtilities.isEventDispatchThread();
        this.input = input;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Chips Challenge");
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        addKeyListener(this.input);

        setSize(new Dimension(640, 480));
        panels = List.of();

        onStart = () -> {
            state = new StartScreen("Start Screen");
            panels = state.panels();
            panels.forEach(this::add);
        };

        onLevelChange = () -> { setState(state.nextLevel()); };
    }

    @Override
    public void update() {
        validate();
        panels.forEach(JPanel::repaint);

        if (GameClock.get().currentTick() == 300) {
            onLevelChange.run();
        }
    }
    
    protected void setState(GameState state) {
        panels.forEach(this::remove);
        this.state = state;
        panels = state.panels();
        panels.forEach(this::add);
    }

    GameState getGameState() {
        return state;
    }
}