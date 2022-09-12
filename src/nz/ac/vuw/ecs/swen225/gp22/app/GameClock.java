package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.ArrayList;
import java.util.List;

public class GameClock implements Subject {
    private static GameClock clock;
    private final ArrayList<Observer> timedElements; // Move the observer stuff to a separate class.
    private static final Runnable onTick = () -> {
        // Do something to determine time to wait for a tick... Need to talk to Borgar about it.
        while(get().running) {
            get().tickIncrement();

            // Tick observers.
            get().update();
        }
    };
    private static final Thread tickThread = new Thread(onTick, "Tick Thread");

    private long tickCount;
    private long levelTickCount;
    private long timePlayed;
    private long timeRemaining;
    private boolean running;

    public static GameClock get() {
        if (clock == null) clock = new GameClock();
        return clock;
    }

    @Override
    public void update() {
        List.copyOf(timedElements).forEach(Observer::update);
    }
    @Override
    public void register(Observer ob) {
        timedElements.add(ob);
    }
    @Override
    public void unregister(Observer ob) {
        timedElements.remove(ob);
    }

    public long currentTick()      { return tickCount; }
    public long currentTime()      { return timePlayed; }
    public long currentLevelTick() { return levelTickCount; }
    public long currentLevelTime() { return timeRemaining; }

    protected void setLevelTime(long time) {
        this.timeRemaining = time;
    }
    protected  void tickIncrement() {
        levelTickCount++;
        tickCount++;
    }
    protected  void resetLevelTick() { levelTickCount = 0L; }

    protected static void stop() {
        get().running = false; // This is bad but it works for now.
    }

    private GameClock() {
        tickCount = 0;
        levelTickCount = 0;
        timePlayed = 0;
        timeRemaining = 0;
        timedElements = new ArrayList<>();
        running = true;
        tickThread.start();
    }
}
