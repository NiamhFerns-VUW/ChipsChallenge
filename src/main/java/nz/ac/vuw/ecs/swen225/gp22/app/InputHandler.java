package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class InputHandler implements KeyListener {
    private final Domain domain;
    private final HashMap<Integer, Runnable> pressed;
    private final HashMap<Integer, Runnable> released;

    public void mvUp() {
        domain.movePlayer(Direction.Up);
    }
    public void mvDown() {
        domain.movePlayer(Direction.Down);
    }
    public void mvLeft() {
        domain.movePlayer(Direction.Left);
    }
    public void mvRight() {
        domain.movePlayer(Direction.Right);
    }

    protected void addBinding(Integer key, Runnable pressed, Runnable released) {
        this.pressed.put(key, pressed);
        this.released.put(key, released);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        System.out.println("Hello");
    }

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
