package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;
import nz.ac.vuw.ecs.swen225.gp22.recorder.Recorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

class InputHandler implements KeyListener {
    private final Domain domain;
    private final Recorder recorder;
    private final HashMap<Integer, Runnable> pressed;
    private final HashMap<Integer, Runnable> released;

    protected void mvUp() {
        domain.movePlayer(Direction.Up);
        recorder.up();
    }
    protected void mvDown() {
        domain.movePlayer(Direction.Down);
        recorder.down();
    }
    protected void mvLeft() {
        domain.movePlayer(Direction.Left);
        recorder.left();
    }
    protected void mvRight() {
        domain.movePlayer(Direction.Right);
        recorder.right();
    }

    protected void addBinding(Integer key, Runnable pressed, Runnable released) {
        this.pressed.put(key, pressed);
        this.released.put(key, released);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        pressed.getOrDefault(keyEvent.getKeyCode(), () -> {}).run();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        released.getOrDefault(keyEvent.getKeyCode(), () -> {}).run();
    }

    InputHandler(Domain domain, Recorder recorder) {
        this.domain = domain;
        this.recorder = recorder;
        pressed = new HashMap<>();
        released = new HashMap<>();

    }
}
