package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class Viewport extends JFrame implements Observer {
    private GameState state;
    private GameHandler owner;
    private final InputHandler input;

    private List<JPanel> panels;

    Runnable onLevelChange;
    Runnable onStart;

    public Viewport(GameHandler owner, InputHandler input) {
        assert SwingUtilities.isEventDispatchThread();
        this.input = input;
        this.owner = owner;

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

        onLevelChange = () -> {
            setState(state.nextLevel());
            System.out.println(state.getClass().getSimpleName());
            if (state instanceof LevelOne) owner.getDomain().startLevel(state.levelName());
            System.out.println("Starting level " + state.levelName());
            if (state instanceof LevelOne) ((LevelOne) state).gameplayPanel().setUp(owner.getDomain());


            // Register for ticks after everything has loaded and the level can start.
            GameClock.get().register(this);
            validate();
        };
    }

    @Override
    public void update() {
        panels.forEach(JPanel::repaint);
    }
    
    protected void setState(GameState state) {
        GameClock.get().unregister(this);
        panels.forEach(this::remove);
        this.state = state;
        panels = state.panels();
        panels.forEach(this::add);
        panels.forEach(System.out::println);
    }

    GameState getGameState() {
        return state;
    }
}