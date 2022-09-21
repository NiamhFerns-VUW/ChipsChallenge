package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final ArrayList<Observer> obs = new ArrayList<>();
    protected final void update() {
        List.copyOf(obs).forEach(Observer::update);
    }
    public final void register(Observer ob) {
        obs.add(ob);
    }
    public final void unregister(Observer ob) {
        obs.remove(ob);
    }
}
