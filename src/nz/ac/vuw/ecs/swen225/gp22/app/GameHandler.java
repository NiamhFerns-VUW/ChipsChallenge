package nz.ac.vuw.ecs.swen225.gp22.app;

public class GameHandler implements Observer {

    public void start() {
        GameClock.get().register(this);
    }

    @Override
    public void update() {
        System.out.println("Tick: " + GameClock.get().currentTick());
    }
}
