package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

class InputHandler implements KeyListener {
    private final Domain domain;
    private final HashMap<Integer, Runnable> pressed;
    private final HashMap<Integer, Runnable> released;

    protected void mvUp() {
        System.out.println("UP");
        // domain.movePlayer(Direction.Up);
    }
    protected void mvDown() {
        System.out.println("DOWN");
        // domain.movePlayer(Direction.Down);
    }
    protected void mvLeft() {
        System.out.println("LEFT");
        // domain.movePlayer(Direction.Left);
    }
    protected void mvRight() {
        System.out.println("RIGHT");
        // domain.movePlayer(Direction.Right);
    }

    protected void addBinding(Integer key, Runnable pressed, Runnable released) {
        this.pressed.put(key, pressed);
        this.released.put(key, released);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        pressed.getOrDefault(keyEvent.getKeyCode(), () -> {}).run();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        released.getOrDefault(keyEvent.getKeyCode(), () -> {}).run();
    }

    InputHandler(Domain domain) {
        this.domain = domain;
        pressed = new HashMap<>();
        released = new HashMap<>();

    }
}
